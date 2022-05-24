package kz.iitu.zakaz_s_soboi.data.local_storage.dto

import androidx.room.Embedded
import androidx.room.Relation
import kz.iitu.zakaz_s_soboi.data.local_storage.entity.CartEntity
import kz.iitu.zakaz_s_soboi.data.local_storage.entity.CartItemEntity
import kz.iitu.zakaz_s_soboi.data.local_storage.entity.ProductEntity
import kz.iitu.zakaz_s_soboi.data.local_storage.entity.RestaurantEntity

data class CartDto(
    @Embedded val cart: CartEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "cart_id",
        entity = CartItemEntity::class
    ) val items: List<CartItemDto>,
    @Relation(
        parentColumn = "restaurant_id",
        entityColumn = "id",
        entity = RestaurantEntity::class
    ) val restaurant: RestaurantEntity
)

data class CartItemDto(
    @Embedded val item: CartItemEntity,
    @Relation(
        parentColumn = "product_id",
        entityColumn = "id",
        entity = ProductEntity::class
    ) val product: ProductEntity
)