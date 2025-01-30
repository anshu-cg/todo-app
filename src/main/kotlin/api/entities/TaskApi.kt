package com.example.api.entities

import kotlinx.serialization.Serializable

@Serializable
data class TaskApi(
    val name: String,
    val description: String,
    val priority: String
)
