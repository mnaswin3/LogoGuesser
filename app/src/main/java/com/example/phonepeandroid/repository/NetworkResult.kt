package com.example.phonepeandroid.repository

import com.example.phonepeandroid.repository.modal.Option
import com.example.phonepeandroid.view.UiState

sealed class NetworkResult<out T> {
    class SUCCESS<T>(val item: T) : NetworkResult<T>()
    class ERROR(val exception: Exception) : NetworkResult<Nothing>()

    fun data(): T? {
        return when (this) {
            is SUCCESS -> item
            else -> null
        }
    }

    fun errorMessage(): String? {
        return when (this) {
            is ERROR -> exception.message
            else -> null
        }
    }
}