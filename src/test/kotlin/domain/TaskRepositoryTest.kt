//package com.example.domain
//
//import io.mockk.*
//import kotlinx.coroutines.runBlocking
//import org.junit.Test
//import kotlin.test.assertEquals
//import kotlin.test.assertNull
//
//class TaskRepositoryTest {
//
//    private val taskRepository: TaskRepository = mockk()
//
//    private val task1 = Task(name = "Task 1", description = "Description 1", priority = "High")
//    private val task2 = Task(name = "Task 2", description = "Description 2", priority = "Medium")
//    private val taskList = listOf(task1, task2)
//
//    @Test
//    fun `test addTask adds a task`() = runBlocking {
//        // Given the task to add
//        val task = task1
//
//        // Mocking the repository method
//        coEvery { taskRepository.addTask(task) } just Runs
//
//        // Call addTask
//        taskRepository.addTask(task)
//
//        // Verify that the addTask method was called
//        coVerify { taskRepository.addTask(task) }
//    }
//
//    @Test
//    fun `test allTasks returns a list of tasks`() = runBlocking {
//        // Mock the repository to return taskList
//        coEvery { taskRepository.allTasks() } returns taskList
//
//        // Call allTasks
//        val tasks = taskRepository.allTasks()
//
//        // Assert that the returned list matches
//        assertEquals(tasks, taskList)
//    }
//
//    @Test
//    fun `test taskByName returns a task`() = runBlocking {
//        // Mock the repository to return task1 when searched by name
//        coEvery { taskRepository.taskByName(task1.name) } returns task1
//
//        // Call taskByName
//        val foundTask = taskRepository.taskByName(task1.name)
//
//        // Assert that the found task matches task1
//        assertEquals(foundTask, task1)
//    }
//
//    @Test
//    fun `test taskByName returns null for unknown task`() = runBlocking {
//        // Mock the repository to return null for an unknown task
//        coEvery { taskRepository.taskByName("Unknown Task") } returns null
//
//        // Call taskByName for a non-existing task
//        val foundTask = taskRepository.taskByName("Unknown Task")
//
//        // Assert that no task is found
//        assertNull(foundTask)
//    }
//
//    @Test
//    fun `test removeTask returns true if task is removed`() = runBlocking {
//        // Mock repository to return true when task is removed
//        coEvery { taskRepository.removeTask(task1.name) } returns true
//
//        // Call removeTask
//        val result = taskRepository.removeTask(task1.name)
//
//        // Assert that the task removal is successful
//        assertEquals(result, true)
//    }
//
//    @Test
//    fun `test removeTask returns false if task not found`() = runBlocking {
//        // Mock repository to return false if task is not found
//        coEvery { taskRepository.removeTask("Nonexistent Task") } returns false
//
//        // Call removeTask for a non-existing task
//        val result = taskRepository.removeTask("Nonexistent Task")
//
//        // Assert that task removal fails for a non-existent task
//        assertEquals(result, false)
//    }
//
//    @Test
//    fun `test updateTask updates and returns updated task`() = runBlocking {
//        val updatedTask = task1.copy(description = "Updated Description", priority = "Medium")
//
//        // Mock repository to return the updated task
//        coEvery { taskRepository.updateTask(task1.name, updatedTask) } returns updatedTask
//
//        // Call updateTask
//        val result = taskRepository.updateTask(task1.name, updatedTask)
//
//        // Assert that the updated task is returned
//        assertEquals(result, updatedTask)
//    }
//}

package com.example.domain

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
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
