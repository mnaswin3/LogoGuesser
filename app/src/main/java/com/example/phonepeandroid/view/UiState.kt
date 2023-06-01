package com.example.phonepeandroid.view

import com.example.phonepeandroid.repository.modal.Option

sealed class UiState<out T> {

    object LOADING : UiState<Nothing>()
    class ERROR(val errorMessage : String) : UiState<Nothing>()
    class SUCCESS<T>(val item: T) : UiState<T>()

}