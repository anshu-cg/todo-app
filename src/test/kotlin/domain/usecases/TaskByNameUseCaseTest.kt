package com.example.domain.usecases

import com.example.domain.entities.Task
import com.example.domain.repositories.TaskRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class TaskByNameUseCaseTest {

    private val taskRepository: TaskRepository = mockk()

    @Test
    fun `should return task by name`() = runBlocking {
        // Given
        val task = Task("Task 1", "Description 1", "High")
        coEvery { taskRepository.taskByName("Task 1") } returns task

        // When
        val result = taskRepository.taskByName("Task 1")

        // Then
        assertEquals(task, result)
        coVerify { taskRepository.taskByName("Task 1") }
    }

    @Test
    fun `should return null if task is not found by name`() = runBlocking {
        // Given
        coEvery { taskRepository.taskByName("Nonexistent Task") } returns null

        // When
        val result = taskRepository.taskByName("Nonexistent Task")

        // Then
        assertEquals(null, result)
        coVerify { taskRepository.taskByName("Nonexistent Task") }
    }

}
