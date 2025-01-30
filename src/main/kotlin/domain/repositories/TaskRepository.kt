package com.example.domain.repositories

import com.example.domain.entities.Task

interface TaskRepository {
    suspend fun allTasks(): List<Task>
    suspend fun taskByName(name: String): Task?
    suspend fun addTask(task: Task)
    suspend fun removeTask(name: String): Boolean
    suspend fun updateTask(name: String, updatedTask: Task): Task?
}
