package com.example.http.components

import com.example.api.TodoApi
import io.ktor.server.application.*

class HttpComponent(private val todoApi: TodoApi) {
    suspend fun allTasks(call: ApplicationCall) = todoApi.allTasks(call)
    suspend fun taskByName(call: ApplicationCall) = todoApi.taskByName(call)
    suspend fun addTask(call: ApplicationCall) = todoApi.addTask(call)
    suspend fun removeTask(call: ApplicationCall) = todoApi.removeTask(call)
    suspend fun updateTask(call: ApplicationCall) = todoApi.updateTask(call)
}