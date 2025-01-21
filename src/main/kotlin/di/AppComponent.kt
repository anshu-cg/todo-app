package com.example.di

import com.example.model.TaskRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules=[AppModule::class])
interface AppComponent {
    fun getTaskRepository(): TaskRepository
}