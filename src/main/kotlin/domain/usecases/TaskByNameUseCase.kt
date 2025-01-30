package com.example.domain.usecases

import com.example.domain.entities.Task
import com.example.domain.repositories.TaskRepository
import javax.inject.Inject

class TaskByNameUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    suspend fun invoke(name: String): Task? {
        return taskRepository.taskByName(name)  // Fetch the task from the repository
    }
}