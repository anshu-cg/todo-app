package com.example.model

import kotlinx.serialization.Serializable

enum class Priority {
    Low, Medium, High, Vital, low, high
}

@Serializable
data class Task(
    val name: String,
    val description: String,
    val priority: Priority
)