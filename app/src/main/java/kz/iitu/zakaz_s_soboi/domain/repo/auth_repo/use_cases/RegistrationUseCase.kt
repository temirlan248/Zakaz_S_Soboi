package kz.iitu.zakaz_s_soboi.domain.repo.auth_repo.use_cases

import kz.iitu.zakaz_s_soboi.domain.repo.auth_repo.AuthorizationRepository
import kz.iitu.zakaz_s_soboi.domain.repo.auth_repo.forms.RegistrationForm
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(
    private val repository: AuthorizationRepository
) {
    suspend operator fun invoke(registrationForm: RegistrationForm) =
        repository.register(registrationForm)
}
