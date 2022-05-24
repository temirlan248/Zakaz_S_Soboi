package kz.iitu.zakaz_s_soboi.domain.model

data class Cart(
    val id: Int,
    val userId: Int,
    val itemList: List<CartItem>
)