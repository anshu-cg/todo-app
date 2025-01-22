package com.example.di

import com.example.model.PostgresTaskRepository
import com.example.model.TaskRepository
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
