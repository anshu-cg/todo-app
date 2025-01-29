package com.example.data.repositories

import com.example.data.TaskTable
import com.example.data.dataToModel
import com.example.data.suspendTransaction
import com.example.domain.Task
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.*

class PostgresRepositoryImpl : PostgresRepository {
    override suspend fun allTasks(): List<Task> = suspendTransaction {
        TaskTable.selectAll().map { row ->
            dataToModel(row) // Convert the row to Task model
        }
    }

    override suspend fun taskByName(name: String): Task? = suspendTransaction {
        TaskTable
            .selectAll().where { TaskTable.name eq name }
            .limit(1)
            .map { row -> dataToModel(row) }
            .firstOrNull()
    }

    override suspend fun addTask(task: Task): Unit = suspendTransaction {
        TaskTable.insert {
            it[name] = task.name
            it[description] = task.description
            it[priority] = task.priority.toString()
        }
    }

    override suspend fun removeTask(name: String): Boolean = suspendTransaction {
        val rowsDeleted = TaskTable.deleteWhere { TaskTable.name eq name }
        rowsDeleted == 1
    }

    override suspend fun updateTask(name: String, updatedTask: Task): Task? = suspendTransaction {
        val updatedRows = TaskTable.update({ TaskTable.name eq name }) {
            it[description] = updatedTask.description
            it[priority] = updatedTask.priority.toString()
        }

        if (updatedRows > 0) {
            TaskTable
                .selectAll().where { TaskTable.name eq name }
                .limit(1)
                .map { row -> dataToModel(row) }
                .firstOrNull()
        } else {
            null
        }
    }
}
