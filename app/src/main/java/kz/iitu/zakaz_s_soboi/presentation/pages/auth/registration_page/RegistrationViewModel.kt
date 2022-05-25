package kz.iitu.zakaz_s_soboi.presentation.pages.auth.registration_page

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kz.iitu.zakaz_s_soboi.domain.repo.auth_repo.forms.RegistrationForm
import kz.iitu.zakaz_s_soboi.domain.repo.auth_repo.use_cases.RegistrationUseCase
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registrationUseCase: RegistrationUseCase
) : ViewModel() {

    companion object {
        private const val PASSWORD_SHORT = "Password must contain at least 8 symbols"
        private const val PASSWORD_LONG = "Password must contain at least 8 symbols"
        private const val PASSWORD_INVALID =
            "Password must contain at least 1 uppercase, 1 lowercase, 1 number and 1 special symbol"

        private const val EMAIL_INVALID = "Email is invalid"

        private const val PASSWORD_NOT_MATCH = "Passwords don't match"

        private val VALID: String? = null
    }

    private var job = Job()
        get() {
            if (field.isCancelled)
                field = Job()
            return field
        }

    private val _registrationState = MutableStateFlow<RegistrationState>(RegistrationState.Empty)
    val registrationState = _registrationState.asStateFlow()

    fun register(email: String, password: String, reEnteredPassword: String) {
        val emailError = validateEmail(email)
        var passwordError = validatePassword(password)

        val reEnteredPasswordError: String? = if (!passwordError.isNullOrEmpty()) {
            passwordError
        } else {
            if (password != reEnteredPassword) {
                passwordError = PASSWORD_NOT_MATCH
                PASSWORD_NOT_MATCH
            } else {
                VALID
            }
        }
        if (!emailError.isNullOrEmpty() || !passwordError.isNullOrEmpty() || !reEnteredPasswordError.isNullOrEmpty()) {
            _registrationState.value = RegistrationState.Error(
                emailError = emailError,
                passwordError = passwordError,
                reEnteredPasswordError = reEnteredPasswordError
            )
            return
        }

        val registrationForm = RegistrationForm(
            email = email,
            password = password
        )
        register(registrationForm)
    }

    fun cancel() = job.cancel()

    fun resetState() {
        _registrationState.value = RegistrationState.Empty
    }

    private fun register(registrationForm: RegistrationForm) {
        viewModelScope.launch(job) {
            registrationUseCase(registrationForm)
                .onStart {
                    _registrationState.value = RegistrationState.Loading
                }
                .catch {
                    _registrationState.value = RegistrationState.Error(error = it.message)
                }
                .collectLatest {
                    _registrationState.value = RegistrationState.Success
                }
        }
    }

    private fun validatePassword(password: String): String? {
        var error: String? = null

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

    private fun validateEmail(email: String): String? {
        var error: String? = null

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            error = EMAIL_INVALID
            return error
        }

        return error
    }

}
