package com.example.http.requests

import com.example.api.usecases.RemoveTaskUseCase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import javax.inject.Inject

class RemoveTaskHttpRequest @Inject constructor(private val removeTaskUseCase: RemoveTaskUseCase) {
    suspend fun removeTask(call: ApplicationCall) {
        val taskName = call.parameters["taskName"]

        if (taskName != null) {
            try {
                // Call the API layer to remove the task
                removeTaskUseCase.invoke(taskName)
                call.respond(HttpStatusCode.OK, "Task removed successfully")
            } catch (e: Exception) {
                // Handle error (e.g., task not found, invalid name)
                call.respond(HttpStatusCode.BadRequest, "Error removing task: ${e.message}")
            }
        } else {
            call.respond(HttpStatusCode.BadRequest, "Invalid task name")
        }
    }
}