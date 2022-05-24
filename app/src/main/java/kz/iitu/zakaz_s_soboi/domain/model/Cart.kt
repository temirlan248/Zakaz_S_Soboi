package kz.iitu.zakaz_s_soboi.domain.model

data class Cart(
    val id: Int,
    val userId: Int,
    val restaurantName: String,
    val restaurantLocation: String,
    val restaurantImage: String,
    val itemList: List<CartItem>
)