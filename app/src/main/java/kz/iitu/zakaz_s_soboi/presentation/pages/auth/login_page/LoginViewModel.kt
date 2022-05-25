package kz.iitu.zakaz_s_soboi.presentation.pages.auth.login_page

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kz.iitu.zakaz_s_soboi.data.provider.UserProvider
import kz.iitu.zakaz_s_soboi.domain.repo.auth_repo.forms.LoginForm
import kz.iitu.zakaz_s_soboi.domain.repo.auth_repo.use_cases.LoginUseCase
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val userProvider: UserProvider
) : ViewModel() {

    companion object {
        private const val EMAIL_INVALID = "Email is invalid"

        private const val PASSWORD_SHORT = "Password must contain at least 8 symbols"
        private const val PASSWORD_LONG = "Password must contain at least 8 symbols"
        private const val PASSWORD_INVALID =
            "Password must contain at least 1 uppercase, 1 lowercase, 1 number and 1 special symbol"
    }

    private var job = Job()
        get() {
            if (field.isCancelled) {
                field = Job()
            }
            return field
        }

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Empty)
    val loginState = _loginState.asStateFlow()

    init {
        if (userProvider.getToken() != -1){
            _loginState.value = LoginState.Success
        }
    }

    fun login(email: String, password: String) {
        _loginState.value = LoginState.Loading
        val emailError = validateEmail(email)
        val passwordError = validatePassword(password)

        if (emailError.isNotEmpty() || passwordError.isNotEmpty()){
            _loginState.value = LoginState.Error(
                emailError = emailError,
                passwordError = passwordError
            )
            return
        }

        val loginForm = LoginForm(email, password)
        viewModelScope.launch(job) {
            loginUseCase(loginForm)
                .onStart {
                    _loginState.value = LoginState.Loading
                }
                .catch {
                    _loginState.value = LoginState.Error(message = it.message.toString())
                }
                .collectLatest {
                    _loginState.value = LoginState.Success
                }
        }
    }

    fun cancel() = job.cancel()

    fun resetState() {
        _loginState.value = LoginState.Empty
    }

    private fun validatePassword(password: String): String {
        var error = ""

        if (password.length < 8) {
            error = PASSWORD_SHORT
            return error
        }

        if (password.length > 16) {
            error = PASSWORD_LONG
            return error
        }

        val passwordREGEX = Pattern.compile(
            "^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 8 characters
                    "$"
        )

        if (!passwordREGEX.matcher(password).matches()) {
            error = PASSWORD_INVALID
            return error
        }

        return error
    }

    private fun validateEmail(email: String): String {
        var error = ""

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            error = EMAIL_INVALID
            return error
        }

        return error
    }
}


