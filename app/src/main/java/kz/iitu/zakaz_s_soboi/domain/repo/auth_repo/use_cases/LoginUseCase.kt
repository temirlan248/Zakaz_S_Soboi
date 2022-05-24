package kz.iitu.zakaz_s_soboi.domain.repo.auth_repo.use_cases

import kz.iitu.zakaz_s_soboi.domain.repo.auth_repo.AuthorizationRepository
import kz.iitu.zakaz_s_soboi.domain.repo.auth_repo.forms.LoginForm
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthorizationRepository
) {
    suspend operator fun invoke(loginForm: LoginForm) =
        repository.login(loginForm)
}
