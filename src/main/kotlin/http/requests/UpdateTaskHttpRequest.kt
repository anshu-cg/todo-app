package com.example.http.requests

import com.example.api.entities.TaskApi
import com.example.api.usecases.UpdateTaskUseCase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import javax.inject.Inject

class UpdateTaskHttpRequest @Inject constructor(private val updateTaskUseCase: UpdateTaskUseCase) {

    suspend fun updateTask(call: ApplicationCall) {
        val name = call.parameters["taskName"]  // Extract the task name from the URL
        val updatedTaskApi = call.receive<TaskApi>()  // Extract the updated task from the request body

        if (name != null) {
            // Check if the task exists by name
            val updatedTask = updateTaskUseCase.invoke(name, updatedTaskApi)

            if (updatedTask != null) {
                call.respond(HttpStatusCode.OK, updatedTask)  // Respond with updated task
            } else {
                call.respond(HttpStatusCode.NotFound, "Task not found")  // Task not found, respond with 404
            }
        } else {
            call.respond(HttpStatusCode.BadRequest, "Invalid task name")  // Invalid task name, respond with 400
        }
    }
}