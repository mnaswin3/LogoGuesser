package com.example.phonepeandroid.usecase

import android.content.Context
import com.example.phonepeandroid.repository.MainRepositoryApi
import com.example.phonepeandroid.repository.NetworkResult
import com.example.phonepeandroid.repository.modal.Option
import com.example.phonepeandroid.repository.modal.OptionContent
import com.example.phonepeandroid.view.UiState
import com.example.phonepeandroid.viewmodel.MainViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class MainUseCase(private val repository: MainRepositoryApi) {

    suspend fun getOptions() = flow {
        when(val result = repository.fetchOptions()) {
            is NetworkResult.SUCCESS -> {
                emit(UiState.SUCCESS(result.data()?.toOptionContentList()!!))
            }
            is NetworkResult.ERROR -> {
                emit(UiState.ERROR(result.errorMessage()!!))
            }
        }
    }

    private fun List<Option>.toOptionContentList() : List<OptionContent>{
        val result = mutableListOf<OptionContent>()
        forEach { option ->
            result.add(
                OptionContent(
                    imgUrl = option.imgUrl,
                    answer = option.name,
                    options = getAllOptions(option.name)
                )
            )
        }
        return result
    }

    private fun getAllOptions(answer: String) : List<Char> {
        val charList = answer.toCharArray().toSet().toMutableList()

        while (charList.size < 15) {
            val randomChar = ('a'..'z').random()
            if (randomChar !in charList) {
                charList.add(randomChar.toUpperCase())
            }
        }

        return charList.shuffled()
    }
}

