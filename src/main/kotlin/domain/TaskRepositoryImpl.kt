package com.example.domain

import com.example.data.repositories.PostgresRepository


class TaskRepositoryImpl(private val postgresRepository: PostgresRepository) : TaskRepository {
    override suspend fun allTasks() = postgresRepository.allTasks()
    override suspend fun taskByName(name: String) = postgresRepository.taskByName(name)
    override suspend fun addTask(task: Task) = postgresRepository.addTask(task)
    override suspend fun removeTask(name: String) = postgresRepository.removeTask(name)
    override suspend fun updateTask(name: String, updatedTask: Task) = postgresRepository.updateTask(name, updatedTask)
}