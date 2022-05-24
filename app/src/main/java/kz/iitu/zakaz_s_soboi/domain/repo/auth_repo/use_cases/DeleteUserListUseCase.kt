package kz.iitu.zakaz_s_soboi.domain.repo.auth_repo.use_cases

import kz.iitu.zakaz_s_soboi.domain.repo.auth_repo.AuthorizationRepository
import javax.inject.Inject

class DeleteUserListUseCase @Inject constructor(
    private val repository: AuthorizationRepository
) {
    suspend operator fun invoke() =
        repository.deleteAll()
}
