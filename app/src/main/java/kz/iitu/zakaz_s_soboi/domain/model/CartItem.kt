package kz.iitu.zakaz_s_soboi.domain.model

data class CartItem(
    val id: Int = 0,
    val productId: Int,
    val productName: String,
    val productPrice: Int,
    var count: Int,
)