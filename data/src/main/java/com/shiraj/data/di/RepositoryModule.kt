package com.shiraj.data.di

import com.shiraj.data.repository.GoodWallRepositoryImpl
import com.shiraj.domain.repository.GoodWallRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindGoodWallRepository(repository: GoodWallRepositoryImpl): GoodWallRepository
}