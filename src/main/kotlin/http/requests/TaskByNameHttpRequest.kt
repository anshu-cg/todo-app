package com.example.http.requests

import com.example.api.usecases.TaskByNameUseCase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import javax.inject.Inject

class TaskByNameHttpRequest @Inject constructor (private val taskByNameUseCase: TaskByNameUseCase){
    suspend fun taskByName(call: ApplicationCall) {
        val name = call.parameters["taskName"]
        if (name != null) {
            val task = taskByNameUseCase.invoke(name)
            if (task != null) {
                call.respond(task)
            } else {
                call.respond(HttpStatusCode.NotFound, "Task not found")
            }
        } else {
            call.respond(HttpStatusCode.BadRequest, "Invalid task name")
        }
    }
}