package kz.iitu.zakaz_s_soboi.domain.repo.restaurant_repo

import kotlinx.coroutines.flow.Flow
import kz.iitu.zakaz_s_soboi.domain.model.Restaurant
import kz.iitu.zakaz_s_soboi.domain.repo.restaurant_repo.form.InsertRestaurantForm

interface RestaurantRepository {
    suspend fun getAll(): Flow<List<Restaurant>>

    suspend fun getById(restaurantId: Int): Flow<Restaurant>

    suspend fun insert(insertRestaurantForm: InsertRestaurantForm): Flow<Unit>
}

