package kz.iitu.zakaz_s_soboi.domain.repo.auth_repo.use_cases

import kz.iitu.zakaz_s_soboi.domain.repo.auth_repo.AuthorizationRepository
import kz.iitu.zakaz_s_soboi.domain.repo.auth_repo.forms.UpdateUserForm
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val repository: AuthorizationRepository
) {
    suspend operator fun invoke(updateUserForm: UpdateUserForm) =
        repository.update(updateUserForm)
}
