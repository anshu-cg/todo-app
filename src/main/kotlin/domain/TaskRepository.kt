package com.example.domain

interface TaskRepository {
    suspend fun allTasks(): List<Task>
    suspend fun tasksByPriority(priority: String): List<Task>
    suspend fun taskByName(name: String): Task?
    suspend fun addTask(task: Task)
    suspend fun removeTask(name: String): Boolean
}