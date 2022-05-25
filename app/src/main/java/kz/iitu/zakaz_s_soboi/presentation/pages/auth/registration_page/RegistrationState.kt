package kz.iitu.zakaz_s_soboi.presentation.pages.auth.registration_page

sealed class RegistrationState {
    object Empty : RegistrationState()
    object Loading : RegistrationState()
    data class Error(
        val emailError: String? = null,
        val passwordError: String? = null,
        val reEnteredPasswordError: String? = null,
        val error: String? = null
    ): RegistrationState()
    object Success : RegistrationState()
}