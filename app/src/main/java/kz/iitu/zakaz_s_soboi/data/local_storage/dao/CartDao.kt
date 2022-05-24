package kz.iitu.zakaz_s_soboi.data.local_storage.dao

import androidx.room.*
import kz.iitu.zakaz_s_soboi.data.local_storage.dto.CartDto
import kz.iitu.zakaz_s_soboi.data.local_storage.entity.CartEntity

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(cartEntity: CartEntity) : Long

    @Delete
    suspend fun delete(vararg cartEntity: CartEntity)

    @Update
    suspend fun update(cartEntity: CartEntity)

    @Transaction
    @Query("SELECT * FROM cart")
    suspend fun getAll(): List<CartDto>

    @Transaction
    @Query("SELECT * FROM cart WHERE user_id=:userId")
    suspend fun findByUserId(userId: Int): List<CartDto>

    @Transaction
    @Query("SELECT * FROM cart WHERE id=:id")
    suspend fun findById(id: Int): CartDto?
}