package kz.iitu.zakaz_s_soboi.domain.repo.auth_repo

import kotlinx.coroutines.flow.Flow
import kz.iitu.zakaz_s_soboi.domain.repo.auth_repo.forms.LoginForm
import kz.iitu.zakaz_s_soboi.domain.repo.auth_repo.forms.RegistrationForm

interface AuthorizationRepository {
    suspend fun login(loginForm: LoginForm): Flow<Unit>

    suspend fun register(registrationForm: RegistrationForm): Flow<Unit>

}