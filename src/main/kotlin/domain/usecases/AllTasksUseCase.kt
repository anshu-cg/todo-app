package com.example.domain.usecases

import com.example.domain.repositories.TaskRepository
import com.example.domain.entities.Task
import javax.inject.Inject

class AllTasksUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend fun invoke(): List<Task> {
        try {
            return taskRepository.allTasks()  // Assuming TaskRepository has a method to fetch all tasks
        } catch (e: Exception) {
            throw e
        }
    }
}