package kz.iitu.zakaz_s_soboi.data.local_storage.dao

import androidx.room.*
import kz.iitu.zakaz_s_soboi.data.local_storage.dto.RestaurantDto
import kz.iitu.zakaz_s_soboi.data.local_storage.entity.RestaurantEntity

@Dao
interface RestaurantDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(restaurants: RestaurantEntity)

    @Delete
    suspend fun delete(restaurant: RestaurantEntity)

    @Query("DELETE FROM restaurant")
    suspend fun deleteAll()

    @Update
    suspend fun update(restaurant: RestaurantEntity)

    @Transaction
    @Query("SELECT * FROM restaurant")
    suspend fun getRestaurantList(): List<RestaurantEntity>

    @Transaction
    @Query("SELECT * FROM restaurant WHERE id=:restaurantId")
    suspend fun getRestaurant(restaurantId: Int): RestaurantDto?

    @Query("SELECT * FROM restaurant WHERE id=:id")
    suspend fun findById(id: Int): RestaurantEntity?

    @Query("DELETE FROM restaurant WHERE id=:restaurantId")
    suspend fun deleteById(restaurantId: Int)
}