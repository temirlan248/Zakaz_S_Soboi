package kz.iitu.zakaz_s_soboi.data.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kz.iitu.zakaz_s_soboi.data.local_storage.dao.RestaurantDao
import kz.iitu.zakaz_s_soboi.data.local_storage.entity.RestaurantEntity
import kz.iitu.zakaz_s_soboi.data.mapper.toRestaurant
import kz.iitu.zakaz_s_soboi.domain.repo.restaurant_repo.form.InsertRestaurantForm
import kz.iitu.zakaz_s_soboi.domain.repo.restaurant_repo.RestaurantRepository
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val restaurantDao: RestaurantDao
) : RestaurantRepository {
    override suspend fun getAll() =
        flow {
            val fetchedRestaurantList = restaurantDao.getRestaurantList()
            val restaurantList = fetchedRestaurantList.map { it.toRestaurant() }
            emit(restaurantList)
        }.flowOn(Dispatchers.IO)

    override suspend fun getById(restaurantId: Int) =
        flow {
            val fetchedRestaurant = restaurantDao.getRestaurant(restaurantId)
                ?: throw Exception("Restaurant not found")
            val restaurant = fetchedRestaurant.toRestaurant()
            emit(restaurant)
        }.flowOn(Dispatchers.IO)

    override suspend fun insert(insertRestaurantForm: InsertRestaurantForm) =
        flow {
            val restaurantEntity = RestaurantEntity(
                imageUrl = insertRestaurantForm.imageUrl,
                location = insertRestaurantForm.location,
                name = insertRestaurantForm.name
            )
            restaurantDao.insert(restaurantEntity)
            emit(Unit)
        }.flowOn(Dispatchers.IO)

}
