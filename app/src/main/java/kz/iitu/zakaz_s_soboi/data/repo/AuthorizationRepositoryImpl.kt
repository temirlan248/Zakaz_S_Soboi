package kz.iitu.zakaz_s_soboi.data.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kz.iitu.zakaz_s_soboi.data.local_storage.dao.UserDao
import kz.iitu.zakaz_s_soboi.data.local_storage.entity.UserEntity
import kz.iitu.zakaz_s_soboi.data.provider.UserProvider
import kz.iitu.zakaz_s_soboi.domain.repo.auth_repo.AuthorizationRepository
import kz.iitu.zakaz_s_soboi.domain.repo.auth_repo.forms.LoginForm
import kz.iitu.zakaz_s_soboi.domain.repo.auth_repo.forms.RegistrationForm
import javax.inject.Inject

class AuthorizationRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val userProvider: UserProvider
) : AuthorizationRepository {
    override suspend fun login(loginForm: LoginForm) =
        flow {
            val fetchedUser = userDao.findByEmailAndPassword(
                email = loginForm.email,
                password = loginForm.password
            ) ?: throw Exception("No user with such email and password")
            userProvider.saveToken(fetchedUser.id)
            emit(Unit)
        }.flowOn(Dispatchers.IO)

    override suspend fun register(registrationForm: RegistrationForm) =
        flow {
            val user = UserEntity(
                email = registrationForm.email,
                password = registrationForm.password
            )
            userDao.insert(user)
            emit(Unit)
        }.flowOn(Dispatchers.IO)
}
