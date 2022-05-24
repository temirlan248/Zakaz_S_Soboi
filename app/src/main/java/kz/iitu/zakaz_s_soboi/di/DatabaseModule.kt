package kz.iitu.zakaz_s_soboi.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kz.iitu.zakaz_s_soboi.data.local_storage.RestaurantDatabase
import kz.iitu.zakaz_s_soboi.data.local_storage.dao.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideRestaurantDatabase(app: Application): RestaurantDatabase {
        return Room.databaseBuilder(
            app,
            RestaurantDatabase::class.java,
            RestaurantDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(db: RestaurantDatabase): UserDao = db.userDao

    @Provides
    @Singleton
    fun provideRestaurantDao(db: RestaurantDatabase): RestaurantDao = db.restaurantDao

    @Provides
    @Singleton
    fun provideCategoryDao(db: RestaurantDatabase): CategoryDao = db.categoryDao

    @Provides
    @Singleton
    fun provideProductDao(db: RestaurantDatabase): ProductDao = db.productDao

    @Provides
    @Singleton
    fun provideCartDao(db: RestaurantDatabase): CartDao = db.cartDao

    @Provides
    @Singleton
    fun provideCartItemDao(db: RestaurantDatabase): CartItemDao = db.cartItemDao
}