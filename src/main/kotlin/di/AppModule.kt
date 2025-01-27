package com.example.di

import com.example.api.PostgresTaskRepository
import com.example.domain.TaskRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule{
    @Provides
    @Singleton
    fun providesTaskRepository(): TaskRepository {
        return PostgresTaskRepository()
    }
}