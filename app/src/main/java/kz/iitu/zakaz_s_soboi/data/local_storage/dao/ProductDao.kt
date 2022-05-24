package kz.iitu.zakaz_s_soboi.data.local_storage.dao

import androidx.room.*
import kz.iitu.zakaz_s_soboi.data.local_storage.entity.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(productEntity: ProductEntity)

    @Delete
    suspend fun delete(productEntity: ProductEntity)

    @Update
    suspend fun update(productEntity: ProductEntity)

    @Query("SELECT * FROM product WHERE id=:id")
    suspend fun findById(id: Int): ProductEntity?

    @Query("SELECT * FROM product WHERE category_id=:categoryId")
    suspend fun findByCategoryId(categoryId: Int): List<ProductEntity>

    @Query("DELETE FROM product WHERE id=:id")
    suspend fun deleteById(id: Int)
}