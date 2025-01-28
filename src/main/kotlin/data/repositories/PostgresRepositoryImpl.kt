package com.example.data.repositories

import com.example.data.TaskDAO
import com.example.data.TaskTable
import com.example.data.daoToModel
import com.example.data.suspendTransaction
import com.example.domain.Task
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere

class PostgresRepositoryImpl : PostgresRepository {
    override suspend fun allTasks(): List<Task> = suspendTransaction {
        TaskDAO.all().map(::daoToModel)
    }

    override suspend fun taskByName(name: String): Task? = suspendTransaction {
        TaskDAO
            .find { (TaskTable.name eq name) }
            .limit(1)
            .map(::daoToModel)
            .firstOrNull()
    }

    override suspend fun addTask(task: Task): Unit = suspendTransaction {
        TaskDAO.new {
            name = task.name
            description = task.description
            priority = task.priority.toString()
        }
    }

    override suspend fun removeTask(name: String): Boolean = suspendTransaction {
        val rowsDeleted = TaskTable.deleteWhere {
            TaskTable.name eq name
        }
        rowsDeleted == 1
    }

    override suspend fun updateTask(name: String, updatedTask: Task): Task? = suspendTransaction {
        val taskToUpdate = TaskDAO.find { TaskTable.name eq name }.firstOrNull()
        taskToUpdate?.apply {
            description = updatedTask.description
            priority = updatedTask.priority.toString()
        }
        taskToUpdate?.let { daoToModel(it) }
    }
}