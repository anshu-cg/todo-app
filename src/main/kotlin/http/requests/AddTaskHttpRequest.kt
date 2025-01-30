package com.example.http.requests

import com.example.api.entities.TaskApi
import com.example.api.usecases.AddTaskUseCase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import javax.inject.Inject

class AddTaskHttpRequest @Inject constructor (private val addTaskUseCase: AddTaskUseCase){
    suspend fun addTask(call: ApplicationCall) {
        try {
            val todoRequest = call.receive<TaskApi>()
            val addedTask = addTaskUseCase.invoke(todoRequest)
            call.respond(HttpStatusCode.Created, addedTask)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, "Error adding task")
        }
    }
}