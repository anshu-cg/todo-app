package com.example.api.usecases

import com.example.domain.usecases.RemoveTaskUseCase
import javax.inject.Inject

class RemoveTaskUseCase @Inject constructor(private val removeTaskUseCase: RemoveTaskUseCase) {
    suspend fun invoke(taskName: String): Boolean {
        try {
            return removeTaskUseCase.invoke(taskName)
        } catch (e: Exception) {
            throw e
        }
    }
}