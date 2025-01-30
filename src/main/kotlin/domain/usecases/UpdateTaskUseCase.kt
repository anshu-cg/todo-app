package com.example.domain.usecases

import com.example.domain.entities.Task
import com.example.domain.repositories.TaskRepository
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    suspend fun invoke(name: String, updatedTask: Task): Task? {
        return taskRepository.updateTask(name, updatedTask) // Delegate to repository for update
    }
}