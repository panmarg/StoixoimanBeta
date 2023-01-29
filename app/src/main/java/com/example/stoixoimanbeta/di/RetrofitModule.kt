package com.example.stoixoimanbeta.di

import com.example.stoixoimanbeta.service.SportsApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    fun providesSportsApi(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://618d3aa7fe09aa001744060a.mockapi.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesSportsApiInterface(retrofit: Retrofit): SportsApiInterface {
        return retrofit.create(SportsApiInterface::class.java)
    }


}