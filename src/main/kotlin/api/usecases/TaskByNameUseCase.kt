package com.example.api.usecases

import com.example.api.entities.TaskApi
import com.example.api.mappers.TaskMapper
import com.example.domain.usecases.TaskByNameUseCase
import javax.inject.Inject

class TaskByNameUseCase @Inject constructor(private val taskByNameUseCase: TaskByNameUseCase, private val taskMapper: TaskMapper) {

    suspend fun invoke(name: String): TaskApi? {
        val task = taskByNameUseCase.invoke(name)
        return task?.let { taskMapper.fromDomainApi(it) }
    }
}