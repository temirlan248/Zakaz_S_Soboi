package kz.iitu.zakaz_s_soboi.domain.model

data class Restaurant(
    val id: Int,
    val name: String,
    val location: String,
    val imageUrl: String,
    val categoryList: List<Category>
)