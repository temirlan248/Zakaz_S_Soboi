package kz.iitu.zakaz_s_soboi.domain.model

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Int,
    val imageUrl: String,
)