package kz.iitu.zakaz_s_soboi.presentation.pages.auth.login_page

sealed class LoginState {
    object Empty : LoginState()
    object Loading : LoginState()
    data class Error(
        val message: String = "",
        val emailError: String = "",
        val passwordError: String = ""
    ) : LoginState()
    object Success : LoginState()
}