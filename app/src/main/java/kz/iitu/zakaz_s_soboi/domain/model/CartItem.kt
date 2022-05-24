package kz.iitu.zakaz_s_soboi.domain.model

data class CartItem(
    val id: Int,
    val productId: Int,
    val count: Int,
)