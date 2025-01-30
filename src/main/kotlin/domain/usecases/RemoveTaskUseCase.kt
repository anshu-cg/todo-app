package com.example.domain.usecases
import com.example.domain.repositories.TaskRepository
import javax.inject.Inject

class RemoveTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    suspend fun invoke(taskName: String): Boolean {
        try {
            val isRemoved = taskRepository.removeTask(taskName)

            if (!isRemoved) {
                throw IllegalArgumentException("Task with name '$taskName' not found")
            }
            return isRemoved// Return true if the task was successfully removed
        } catch (e: Exception) {
            // Handle any exception (e.g., logging, wrapping exception)
            throw e
        }
    }
}
