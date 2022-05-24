package kz.iitu.zakaz_s_soboi.data.mapper

import kz.iitu.zakaz_s_soboi.data.local_storage.dto.CartDto
import kz.iitu.zakaz_s_soboi.data.local_storage.dto.CartItemDto
import kz.iitu.zakaz_s_soboi.domain.model.Cart
import kz.iitu.zakaz_s_soboi.domain.model.CartItem

fun CartDto.toCart() =
    Cart(
        id = cart.id,
        userId = cart.userId,
        itemList = items.map { it.toCartItem() },
        restaurantImage = restaurant.imageUrl,
        restaurantName = restaurant.name,
        restaurantLocation = restaurant.location
    )

fun CartItemDto.toCartItem() =
    CartItem(
        id = item.id,
        productName = product.name,
        productImage = product.imageUrl,
        productDescription = product.description,
        productPrice = product.price,
        count = item.count
    )