package com.example.http.routes

import com.example.http.components.HttpComponent
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(httpComponent: HttpComponent) {
    routing {
        get("/") {
            call.respondText("Welcome to todo App")
        }
        route("/tasks") {
            get {
                httpComponent.allTasks(call)
            }
            get("{id}") {
                httpComponent.taskByName(call)
            }
            post {
                httpComponent.addTask(call)
            }
            delete("{taskName}") {
                httpComponent.removeTask(call)
            }
            put("{taskName}") {
                httpComponent.updateTask(call)
            }
        }

    }
}