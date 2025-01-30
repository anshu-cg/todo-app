package com.example.http.requests

import com.example.api.usecases.AllTasksUseCase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import javax.inject.Inject

class AllTasksHttpRequest @Inject constructor(private val allTasksUseCase: AllTasksUseCase) {

    suspend fun allTasks(call: ApplicationCall) {
        try {
            val tasks = allTasksUseCase.invoke()  // Get tasks via the API use case
            call.respond(HttpStatusCode.OK, tasks)  // Respond with the tasks
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, "Error fetching tasks: ${e.message}")
        }
    }
}