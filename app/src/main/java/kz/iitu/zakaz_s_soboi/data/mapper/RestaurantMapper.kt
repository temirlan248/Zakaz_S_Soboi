package kz.iitu.zakaz_s_soboi.data.mapper

import kz.iitu.zakaz_s_soboi.data.local_storage.dto.CategoryDto
import kz.iitu.zakaz_s_soboi.data.local_storage.dto.RestaurantDto
import kz.iitu.zakaz_s_soboi.data.local_storage.entity.ProductEntity
import kz.iitu.zakaz_s_soboi.data.local_storage.entity.RestaurantEntity
import kz.iitu.zakaz_s_soboi.domain.model.Category
import kz.iitu.zakaz_s_soboi.domain.model.Product
import kz.iitu.zakaz_s_soboi.domain.model.Restaurant

fun RestaurantEntity.toRestaurant() =
    Restaurant(
        id = id,
        imageUrl = imageUrl,
        name = name,
        location = location,
        categoryList = listOf()
    )

fun RestaurantDto.toRestaurant() =
    Restaurant(
        id = restaurant.id,
        imageUrl = restaurant.imageUrl,
        name = restaurant.name,
        location = restaurant.location,
        categoryList = categoryList.map { it.toCategory() }
    )

fun CategoryDto.toCategory() =
    Category(
        id = category.id,
        name = category.name,
        productList = productList.map { it.toProduct() }
    )

fun ProductEntity.toProduct() =
    Product(
        id = id,
        name = name,
        description = description,
        price = price,
        imageUrl = imageUrl
    )