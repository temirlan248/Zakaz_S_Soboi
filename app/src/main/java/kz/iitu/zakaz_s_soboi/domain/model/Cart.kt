package kz.iitu.zakaz_s_soboi.domain.model

data class Cart(
    val id: Int = 0,
    val userId: Int,
    val restaurantId: Int,
    val restaurantName: String,
    val restaurantLocation: String,
    val restaurantImage: String,
    val itemList: MutableList<CartItem>
)