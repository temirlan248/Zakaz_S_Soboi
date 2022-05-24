package kz.iitu.zakaz_s_soboi.data.local_storage.dto

import androidx.room.Embedded
import androidx.room.Relation
import kz.iitu.zakaz_s_soboi.data.local_storage.entity.CategoryEntity
import kz.iitu.zakaz_s_soboi.data.local_storage.entity.ProductEntity
import kz.iitu.zakaz_s_soboi.data.local_storage.entity.RestaurantEntity

data class RestaurantDto(
    @Embedded val restaurant: RestaurantEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "restaurant_id",
        entity = CategoryEntity::class
    ) val categoryList: List<CategoryDto>
)

data class CategoryDto(
    @Embedded val category: CategoryEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "category_id",
        entity = ProductEntity::class
    ) val productList: List<ProductEntity>
)
