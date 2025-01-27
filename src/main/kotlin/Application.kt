package com.example

import com.example.data.configureDatabases
import com.example.http.configureRouting
import com.example.di.AppComponent
import io.ktor.server.application.*
import com.example.di.DaggerAppComponent
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val appComponent: AppComponent= DaggerAppComponent.create()
    val repository=appComponent.getTaskRepository()

    configureSerialization()
    configureDatabases()
    configureRouting(repository)
}