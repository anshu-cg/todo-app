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
            get { httpComponent.allTasksService.allTasks(call) }
            get("{taskName}") { httpComponent.taskByNameService.taskByName(call) }
            post { httpComponent.addTaskService.addTask(call) }
            delete("{taskName}") { httpComponent.removeTaskService.removeTask(call) }
            put("{taskName}") { httpComponent.updateTaskService.updateTask(call) }
        }
    }
}