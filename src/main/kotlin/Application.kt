package com.example

import com.example.data.configureDatabases
import com.example.http.routes.configureRouting
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
    val appComponent= DaggerAppComponent.create()
    val repository=appComponent.httpComponent()

    configureSerialization()
    configureDatabases()
    configureRouting(repository)
}