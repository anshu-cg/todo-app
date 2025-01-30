package com.example.api.usecases

import com.example.api.entities.TaskApi
import com.example.api.mappers.TaskMapper
import com.example.domain.usecases.AddTaskUseCase
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(private val addTaskUseCase: AddTaskUseCase, private val taskMapper: TaskMapper) {

    suspend fun invoke(taskApi: TaskApi) {
        try {
            val task = taskMapper.toDomainApi(taskApi)
            addTaskUseCase.invoke(task)
        } catch (e: Exception) {
            throw e
        }
    }
}
