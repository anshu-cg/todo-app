package com.example.api.usecases

import com.example.api.entities.TaskApi
import com.example.api.mappers.TaskMapper
import com.example.domain.usecases.UpdateTaskUseCase
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(private val updateTaskUseCase: UpdateTaskUseCase, private val taskMapper: TaskMapper) {

    suspend fun invoke(name: String, updatedTaskApi: TaskApi): TaskApi? {
        val updatedTask = taskMapper.toDomainApi(updatedTaskApi)
        val updatedDomainTask = updateTaskUseCase.invoke(name, updatedTask)
        return updatedDomainTask?.let { taskMapper.fromDomainApi(it) }
    }
}
