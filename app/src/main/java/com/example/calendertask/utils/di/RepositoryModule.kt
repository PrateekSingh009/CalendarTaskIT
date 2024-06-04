package com.example.calendertask.utils.di

import com.example.calendertask.main.api.ApiService
import com.example.calendertask.main.data.ListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(api : ApiService) : ListRepository {
        return ListRepository(api)
    }
}