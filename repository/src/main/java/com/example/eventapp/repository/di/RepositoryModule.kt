package com.example.eventapp.repository.di

import com.example.eventapp.domain.repository.EventsRepository
import com.example.eventapp.repository.repository.EventsRepositoryImpl
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
    abstract fun provideLoyaltyRepository(repository: EventsRepositoryImpl): EventsRepository
}
