package kz.iitu.zakaz_s_soboi.domain.repo.category_repo.forms

data class UpdateCategoryForm(
    val categoryId: Int,
    val name: String,
    val restaurantId: Int,
)
