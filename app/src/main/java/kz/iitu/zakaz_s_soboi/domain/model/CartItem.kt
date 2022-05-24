package kz.iitu.zakaz_s_soboi.domain.model

data class CartItem(
    val id: Int,
    val productName: String,
    val productImage: String,
    val productDescription: String,
    val productPrice: Int,
    val count: Int,
)