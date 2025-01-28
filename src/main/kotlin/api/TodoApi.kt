package com.example.api

import com.example.domain.Task
import com.example.domain.TaskRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class TodoApi(private val taskRepository: TaskRepository){
    suspend fun allTasks(call: ApplicationCall) {
        try {
            call.respond(taskRepository.allTasks())
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, "Error retrieving tasks")
        }
    }

    suspend fun taskByName(call: ApplicationCall) {
        val name = call.parameters["taskName"]
        if (name != null) {
            val task = taskRepository.taskByName(name)
            if (task != null) {
                call.respond(task)
            } else {
                call.respond(HttpStatusCode.NotFound, "Task not found")
            }
        } else {
            call.respond(HttpStatusCode.BadRequest, "Invalid task name")
        }
    }

    suspend fun addTask(call: ApplicationCall) {
        try {
            val todoRequest = call.receive<Task>()
            val addedTask = taskRepository.addTask(todoRequest)
            call.respond(HttpStatusCode.Created, addedTask)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, "Error adding task")
        }
    }

    suspend fun removeTask(call: ApplicationCall) {
        val name = call.parameters["taskName"]
        if (name != null) {
            call.respond(HttpStatusCode.OK, "Task removed successfully")
        } else {
            call.respond(HttpStatusCode.BadRequest, "Invalid task name")
        }
    }
    suspend fun updateTask(call: ApplicationCall) {
        val name = call.parameters["taskName"]
        val updatedTask = call.receive<Task>()
        if (name != null) {
            val existingTask = taskRepository.taskByName(name)
            if (existingTask != null) {
                val updated = taskRepository.updateTask(name, updatedTask)
                call.respond(HttpStatusCode.OK, updated ?: HttpStatusCode.NotFound)
            } else {
                call.respond(HttpStatusCode.NotFound, "Task not found")
            }
        } else {
            call.respond(HttpStatusCode.BadRequest, "Invalid task name")
        }
    }
}