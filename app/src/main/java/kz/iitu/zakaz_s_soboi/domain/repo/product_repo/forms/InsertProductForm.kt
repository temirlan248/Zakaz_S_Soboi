package kz.iitu.zakaz_s_soboi.domain.repo.product_repo.forms

data class InsertProductForm(
    val name: String,
    val description: String,
    val price: Int,
    val imageUrl: String,
    val categoryId: Int
)
