package com.example.http.components

import com.example.di.AppModule
import com.example.http.requests.*
import dagger.Component

@Component(modules = [AppModule::class])
interface HttpComponent{
    val allTasksService: AllTasksHttpRequest
    val taskByNameService: TaskByNameHttpRequest
    val addTaskService: AddTaskHttpRequest
    val removeTaskService: RemoveTaskHttpRequest
    val updateTaskService: UpdateTaskHttpRequest

}