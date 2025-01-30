package com.example.domain.usecases

import com.example.domain.entities.Task
import com.example.domain.repositories.TaskRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class AddTaskUseCaseTest {

    private val taskRepository: TaskRepository = mockk()

    @Test
    fun `should add a task`() = runBlocking {
        // Given
        val task = Task("Task 1", "Description 1", "High")
        coEvery { taskRepository.addTask(task) } returns Unit

        // When
        taskRepository.addTask(task)

        // Then
        coVerify { taskRepository.addTask(task) }
    }

}
