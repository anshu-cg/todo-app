package com.example.domain.usecases

import com.example.domain.entities.Task
import com.example.domain.repositories.TaskRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class UpdateTaskUseCaseTest {

    private val taskRepository: TaskRepository = mockk()

    @Test
    fun `should update task by name`() = runBlocking {
        // Given
        val oldTask = Task("Task 1", "Description 1", "High")
        val updatedTask = Task("Task 1", "Updated Description", "Medium")
        coEvery { taskRepository.updateTask("Task 1", updatedTask) } returns updatedTask

        // When
        val result = taskRepository.updateTask("Task 1", updatedTask)

        // Then
        assertEquals(updatedTask, result)
        coVerify { taskRepository.updateTask("Task 1", updatedTask) }
    }

}
