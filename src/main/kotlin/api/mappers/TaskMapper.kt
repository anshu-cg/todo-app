package com.example.api.mappers

import com.example.api.entities.TaskApi
import com.example.domain.entities.Task

class TaskMapper {
    fun fromDomainApi(task: Task): TaskApi = TaskApi(
        name = task.name,
        description = task.description,
        priority = task.priority
    )

    fun toDomainApi(taskApi: TaskApi): Task = Task(
        name = taskApi.name,
        description = taskApi.description,
        priority = taskApi.priority
    )
}
