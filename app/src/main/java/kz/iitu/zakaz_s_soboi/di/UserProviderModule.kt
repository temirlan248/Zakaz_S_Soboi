package kz.iitu.zakaz_s_soboi.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kz.iitu.zakaz_s_soboi.data.provider.UserProvider
import kz.iitu.zakaz_s_soboi.data.provider.UserProviderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserProviderModule {
    @Singleton
    @Binds
    abstract fun provideUserProvider(userProviderImpl: UserProviderImpl): UserProvider
}