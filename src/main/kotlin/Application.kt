package com.example
//
import io.ktor.server.application.*
import io.ktor.http.HttpStatusCode
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.application.install
import io.ktor.server.request.*
import io.ktor.server.routing.*

import io.ktor.server.request.receive
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.request.ApplicationRequest
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.response.*
//import io.ktor.features.StatusPages
//import io.ktor.http.HttpStatusCode
//import io.ktor.serialization.kotlinx.json
import kotlinx.serialization.Serializable

val todos = mutableListOf<Todoitem>()
var nextid = 1

@Serializable
data class Todoitem(
    val id: Int=0,
    val title: String,
    val description: String,
    val completed: Boolean = false
)

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }

    routing {
        get("/"){
            call.respondText("Todo List App")
        }

        route("/todos") {
            get {
                call.respond(HttpStatusCode.OK, todos)
            }

            post {

                val todo = call.receive<Todoitem>()
                val newtodo=todo.copy(id= nextid++)
                todos.add(newtodo)
                call.respond(HttpStatusCode.Created, newtodo)
            }

            route("/{id}") {
                get {
                    val id = call.parameters["id"]?.toIntOrNull()
                    val todo = todos.find { it.id == id }
                    if (todo != null) {
                        call.respond(todo)
                    } else {
                        call.respond(HttpStatusCode.NotFound, "Todo not found")
                    }
                }

                put {
                    val id = call.parameters["id"]?.toIntOrNull()
                    val todo = call.receive<Todoitem>()
                    val existingTodoIndex = todos.indexOfFirst { it.id == id }
                    if (existingTodoIndex != -1) {
                        todos[existingTodoIndex] = todo
                        call.respond(HttpStatusCode.OK, todo)
                    } else {
                        call.respond(HttpStatusCode.NotFound, "Todo not found")
                    }
                }

                delete {
                    val id = call.parameters["id"]?.toIntOrNull()
                    val todo = todos.find { it.id == id }
                    if (todo != null) {
                        todos.remove(todo)
                        call.respond(HttpStatusCode.NoContent)
                    } else {
                        call.respond(HttpStatusCode.NotFound, "Todo not found")
                    }
                }
            }
        }
    }
}



fun main() {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}
