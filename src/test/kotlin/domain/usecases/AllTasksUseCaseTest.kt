package com.example.domain.usecases

import com.example.domain.entities.Task
import com.example.domain.repositories.TaskRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class AllTasksUseCaseTest {
    private val taskRepository: TaskRepository = mockk()

    @Test
    fun `should return all tasks`() = runBlocking {
        // Given
        val taskList = listOf(
            Task("Task 1", "Description 1", "High"),
            Task("Task 2", "Description 2", "Medium")
        )
        coEvery { taskRepository.allTasks() } returns taskList

        // When
        val result = taskRepository.allTasks()

        // Then
        assertEquals(taskList, result)
        coVerify { taskRepository.allTasks() }
    }
}
