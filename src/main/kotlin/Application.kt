package com.example


import com.example.di.AppComponent
import io.ktor.server.application.*
import com.example.di.DaggerAppComponent

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