package com.example.di

import com.example.api.TodoApi
import com.example.data.repositories.PostgresRepository
import com.example.data.repositories.PostgresRepositoryImpl
import com.example.domain.TaskRepository
import com.example.domain.TaskRepositoryImpl
import com.example.http.components.HttpComponent

import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun providePostgresRepository(): PostgresRepository = PostgresRepositoryImpl()

    @Provides
    fun provideTaskRepository(postgresRepository: PostgresRepository): TaskRepository = TaskRepositoryImpl(postgresRepository)

    @Provides
    fun provideHttpComponent(todoApi: TodoApi): HttpComponent = HttpComponent(todoApi)

    @Provides
    fun provideTodoApi(taskRepository: TaskRepository): TodoApi=TodoApi(taskRepository)
}