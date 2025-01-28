package com.example.di

import dagger.Component
import javax.inject.Singleton
import com.example.http.components.HttpComponent


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun httpComponent(): HttpComponent
}