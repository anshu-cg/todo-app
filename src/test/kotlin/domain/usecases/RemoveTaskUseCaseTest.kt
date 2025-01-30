package com.example.domain.usecases

import com.example.domain.repositories.TaskRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class RemoveTaskUseCaseTest {

    private val taskRepository: TaskRepository = mockk()

    @Test
    fun `should remove a task by name`() = runBlocking {
        // Given
        coEvery { taskRepository.removeTask("Task 1") } returns true

        // When
        val result = taskRepository.removeTask("Task 1")

        // Then
        assertEquals(true, result)
        coVerify { taskRepository.removeTask("Task 1") }
    }

    @Test
    fun `should return false when removing non-existent task`() = runBlocking {
        // Given
        coEvery { taskRepository.removeTask("Nonexistent Task") } returns false

        // When
        val result = taskRepository.removeTask("Nonexistent Task")

        // Then
        assertEquals(false, result)
        coVerify { taskRepository.removeTask("Nonexistent Task") }
    }

}
