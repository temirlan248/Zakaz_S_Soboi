package kz.iitu.zakaz_s_soboi.data.local_storage

import androidx.room.Database
import androidx.room.RoomDatabase
import kz.iitu.zakaz_s_soboi.data.local_storage.dao.*
import kz.iitu.zakaz_s_soboi.data.local_storage.entity.*

@Database(
    entities = [
        ProductEntity::class, CategoryEntity::class, RestaurantEntity::class,
        UserEntity::class, CartEntity::class, CartItemEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class RestaurantDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val restaurantDao: RestaurantDao
    abstract val categoryDao: CategoryDao
    abstract val productDao: ProductDao
    abstract val cartDao: CartDao
    abstract val cartItemDao: CartItemDao

    companion object {
        const val DATABASE_NAME = "restaurant_database"
    }

}