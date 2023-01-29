package com.example.stoixoimanbeta.di

import com.example.stoixoimanbeta.repositories.SportsRepository
import com.example.stoixoimanbeta.repositoriesImpl.SportsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun providesSportsRepository(sportsRepositoryImpl: SportsRepositoryImpl): SportsRepository
}