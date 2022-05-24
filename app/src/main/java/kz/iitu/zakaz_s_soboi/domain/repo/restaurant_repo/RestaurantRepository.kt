package kz.iitu.zakaz_s_soboi.domain.repo.restaurant_repo

import kz.iitu.zakaz_s_soboi.domain.model.Restaurant
import kz.iitu.zakaz_s_soboi.domain.repo.restaurant_repo.form.UpdateRestaurantForm

interface RestaurantRepository {
    suspend fun getAll(): List<Restaurant>

    suspend fun getById(restaurantId: Int): Restaurant?

    suspend fun insert(insertRestaurantForm: InsertRestaurantForm)

    suspend fun delete(restaurantId: Int)

    suspend fun deleteAll()

    suspend fun update(updateRestaurantForm: UpdateRestaurantForm)
}

