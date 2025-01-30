package com.example.domain.usecases

import com.example.domain.entities.Task
import com.example.domain.repositories.TaskRepository
import java.lang.Exception
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend fun invoke(task: Task) {
        try {
            taskRepository.addTask(task) // Persisting task to DB
        } catch (e: Exception) {
            // Handle exception if needed
            throw e
        }
    }
}
