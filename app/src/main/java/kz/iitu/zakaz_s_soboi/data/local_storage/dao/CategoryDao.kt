package kz.iitu.zakaz_s_soboi.data.local_storage.dao

import androidx.room.*
import kz.iitu.zakaz_s_soboi.data.local_storage.entity.CategoryEntity

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(categoryEntity: CategoryEntity)

    @Delete
    suspend fun delete(vararg categoryEntity: CategoryEntity)

    @Update
    suspend fun update(categoryEntity: CategoryEntity)

    @Query("SELECT * FROM category WHERE id=:id")
    suspend fun findById(id: Int): CategoryEntity?

    @Query("SELECT * FROM category")
    suspend fun getAll(): List<CategoryEntity>

    @Query("SELECT * FROM category WHERE restaurant_id=:restaurantId")
    suspend fun findByRestaurant(restaurantId: Int): List<CategoryEntity>

    @Query("DELETE FROM category WHERE id=:categoryId")
    suspend fun deleteById(categoryId: Int)
}