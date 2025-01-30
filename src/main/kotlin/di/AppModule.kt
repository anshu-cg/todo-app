package com.example.di

import com.example.api.mappers.TaskMapper
import com.example.data.repositories.PostgresRepository
import com.example.domain.repositories.TaskRepository

import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideTaskRepository(postgresRepository: PostgresRepository): TaskRepository = postgresRepository

    @Provides
    fun providePostgresRepository(): PostgresRepository = PostgresRepository()

    @Provides
    fun provideTaskMapper(): TaskMapper = TaskMapper()
}

