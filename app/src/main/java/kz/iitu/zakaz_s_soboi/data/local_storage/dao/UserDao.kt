package kz.iitu.zakaz_s_soboi.data.local_storage.dao

import androidx.room.*
import kz.iitu.zakaz_s_soboi.data.local_storage.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: UserEntity)

    @Delete
    suspend fun delete(user: UserEntity)

    @Query("DELETE FROM user")
    suspend fun deleteAll()

    @Query("DELETE FROM user WHERE id=:id")
    suspend fun deleteById(id: Int)

    @Update
    suspend fun update(user: UserEntity)

    @Query("SELECT * FROM user WHERE email=:email AND password=:password")
    suspend fun findByEmailAndPassword(email: String, password: String): UserEntity?

    @Query("SELECT * FROM user WHERE id=:id")
    suspend fun findById(id: Int): UserEntity?

    @Query("SELECT * FROM user")
    suspend fun getAll(): List<UserEntity>
}