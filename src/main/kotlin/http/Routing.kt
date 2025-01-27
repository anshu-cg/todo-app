package com.example.http

import com.example.domain.Task
import com.example.domain.TaskRepository
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(repository: TaskRepository) {
    routing {
        get("/") {
            call.respondText("Welcome to the Todo App")
        }

        route("/tasks") {
            get {
                val tasks = repository.allTasks()
                call.respond(tasks)
            }

            get("/{taskName}") {
                val name = call.parameters["taskName"]
                if (name == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }
                val task = repository.taskByName(name)
                if (task == null) {
                    call.respond(HttpStatusCode.NotFound)
                    return@get
                }
                call.respond(task)
            }

            post {
                try {
                    val task = call.receive<Task>()
                    repository.addTask(task)
                    call.respond(HttpStatusCode.OK, "task successfully added")
                } catch (ex: IllegalStateException) {
                    call.respond(HttpStatusCode.BadRequest)
                } catch (ex: JsonConvertException) {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }

            delete("/{taskName}") {
                val name = call.parameters["taskName"]
                if (name == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@delete
                }
                if (repository.removeTask(name)) {
                    call.respond(HttpStatusCode.OK, "Successfully removed task $name")
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
            put("/{taskName}") {
                val name = call.parameters["taskName"]
                if (name == null) {
                    call.respond(HttpStatusCode.BadRequest, "Task name is required")
                    return@put
                }
                val updatedTask = try {
                    call.receive<Task>()
                } catch (ex: Exception) {
                    call.respond(HttpStatusCode.BadRequest, "Invalid task data")
                    return@put
                }
                val task = repository.updateTask(name, updatedTask)
                if (task != null) {
                    call.respond(HttpStatusCode.OK, task)
                } else {
                    call.respond(HttpStatusCode.NotFound, "Task with name $name not found")
                }
            }
        }
    }
}