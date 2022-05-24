package kz.iitu.zakaz_s_soboi.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kz.iitu.zakaz_s_soboi.data.repo.*
import kz.iitu.zakaz_s_soboi.domain.repo.auth_repo.AuthorizationRepository
import kz.iitu.zakaz_s_soboi.domain.repo.cart_repo.CartRepository
import kz.iitu.zakaz_s_soboi.domain.repo.category_repo.CategoryRepository
import kz.iitu.zakaz_s_soboi.domain.repo.product_repo.ProductRepository
import kz.iitu.zakaz_s_soboi.domain.repo.restaurant_repo.RestaurantRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun provideAuthorizationRepository(authorizationRepositoryImpl: AuthorizationRepositoryImpl): AuthorizationRepository

    @Singleton
    @Binds
    abstract fun provideRestaurantRepository(restaurantRepositoryImpl: RestaurantRepositoryImpl): RestaurantRepository

    @Singleton
    @Binds
    abstract fun provideCartRepository(cartRepositoryImpl: CartRepositoryImpl): CartRepository

    @Singleton
    @Binds
    abstract fun provideCategoryRepository(categoryRepositoryImpl: CategoryRepositoryImpl): CategoryRepository


    @Singleton
    @Binds
    abstract fun provideProductRepository(productRepositoryImpl: ProductRepositoryImpl): ProductRepository
}