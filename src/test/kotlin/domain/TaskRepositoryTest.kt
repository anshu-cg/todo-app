package com.example.domain

import com.example.domain.entities.Task
import com.example.domain.repositories.TaskRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class TaskRepositoryTest {

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
    fun `should add a task`() = runBlocking {
        // Given
        val task = Task("Task 1", "Description 1", "High")
        coEvery { taskRepository.addTask(task) } returns Unit

        // When
        taskRepository.addTask(task)

        // Then
        coVerify { taskRepository.addTask(task) }
    }

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
