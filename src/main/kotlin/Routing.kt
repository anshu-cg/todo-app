package com.example

import com.example.model.Task
import com.example.model.TaskRepository
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

            get("/byName/{taskName}") {
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
                    call.respond(HttpStatusCode.BadRequest, "Name is required")
                    return@put
                }
                val task = repository.taskByName(name)
                if(task == null) {
                    call.respond(HttpStatusCode.NotFound, "Task not found")
                    return@put
                }
                try{
                    val updatedTask=call.receive<Task>()
                    if(updatedTask.name!=name){
                        call.respond(HttpStatusCode.BadRequest, "Task name mismatch")
                        return@put
                    }
                    repository.removeTask(name)
                    repository.addTask(updatedTask)
                    call.respond(HttpStatusCode.OK, "Task updated successfully")
                }catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, "Invalid request: ${e.message}")
                }
            }
        }
    }
}
