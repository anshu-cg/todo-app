package com.example

import com.example.data.configureDatabases
import com.example.http.routes.configureRouting
import io.ktor.server.application.*
import com.example.http.components.DaggerHttpComponent
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
    val httpComponent= DaggerHttpComponent.create()

    configureSerialization()
    configureDatabases()
    configureRouting(httpComponent)
}