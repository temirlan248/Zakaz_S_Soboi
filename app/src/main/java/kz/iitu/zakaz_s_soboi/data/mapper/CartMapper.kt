package kz.iitu.zakaz_s_soboi.data.mapper

import kz.iitu.zakaz_s_soboi.data.local_storage.dto.CartDto
import kz.iitu.zakaz_s_soboi.data.local_storage.dto.CartItemDto
import kz.iitu.zakaz_s_soboi.domain.model.Cart
import kz.iitu.zakaz_s_soboi.domain.model.CartItem

fun CartDto.toCart() =
    Cart(
        id = cart.id,
        userId = cart.userId,
        itemList = items.map { it.toCartItem() } as MutableList<CartItem>,
        restaurantImage = restaurant.imageUrl,
        restaurantName = restaurant.name,
        restaurantLocation = restaurant.location,
        restaurantId = restaurant.id
    )

fun CartItemDto.toCartItem() =
    CartItem(
        id = item.id,
        productId = product.id,
        productName = product.name,
        productPrice = product.price,
        count = item.count
    )