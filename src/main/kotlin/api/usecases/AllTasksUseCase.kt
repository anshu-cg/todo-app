package com.example.api.usecases

import com.example.api.mappers.TaskMapper
import com.example.api.entities.TaskApi
import com.example.domain.usecases.AllTasksUseCase
import javax.inject.Inject

class AllTasksUseCase @Inject constructor(private val allTasksUseCase: AllTasksUseCase, private val taskMapper: TaskMapper) {
    suspend fun invoke(): List<TaskApi> {
        val tasks= allTasksUseCase.invoke()
        return tasks.map{ taskMapper.fromDomainApi(it) }
    }
}