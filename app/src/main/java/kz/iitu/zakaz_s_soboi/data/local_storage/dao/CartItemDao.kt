package kz.iitu.zakaz_s_soboi.data.local_storage.dao

import androidx.room.*
import kz.iitu.zakaz_s_soboi.data.local_storage.dto.CartDto
import kz.iitu.zakaz_s_soboi.data.local_storage.entity.CartEntity
import kz.iitu.zakaz_s_soboi.data.local_storage.entity.CartItemEntity

@Dao
interface CartItemDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(cartItemEntity: CartItemEntity)

    @Delete
    suspend fun delete(vararg cartItemEntity: CartItemEntity)

    @Update
    suspend fun update(cartItemEntity: CartItemEntity)

    @Query("SELECT * FROM cart_item")
    suspend fun getAll(): List<CartItemEntity>

}