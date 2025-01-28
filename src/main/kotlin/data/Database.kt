package com.example.data

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.*

fun Application.configureDatabases() {
    Database.connect(
        "jdbc:postgresql://localhost:5432/todolist",
        user = "postgres",
        password = "03Anshu@15"
    )
}