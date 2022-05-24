package kz.iitu.zakaz_s_soboi.domain.repo.auth_repo

import kz.iitu.zakaz_s_soboi.domain.model.User
import kz.iitu.zakaz_s_soboi.domain.repo.auth_repo.forms.LoginForm
import kz.iitu.zakaz_s_soboi.domain.repo.auth_repo.forms.RegistrationForm
import kz.iitu.zakaz_s_soboi.domain.repo.auth_repo.forms.UpdateUserForm

interface AuthorizationRepository {
    suspend fun login(loginForm: LoginForm)

    suspend fun register(registrationForm: RegistrationForm)

    suspend fun getAll(): List<User>

    suspend fun getById(userId: Int): User?

    suspend fun delete(userId: Int)

    suspend fun deleteAll()

    suspend fun update(updateUserForm: UpdateUserForm)
}