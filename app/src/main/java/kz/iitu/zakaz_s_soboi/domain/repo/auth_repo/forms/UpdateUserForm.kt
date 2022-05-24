package kz.iitu.zakaz_s_soboi.domain.repo.auth_repo.forms

data class UpdateUserForm(
    val userId: Int,
    val password: String,
)