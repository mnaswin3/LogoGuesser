package com.example.phonepeandroid.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phonepeandroid.repository.MainRepositoryImpl
import com.example.phonepeandroid.repository.modal.Option
import com.example.phonepeandroid.repository.modal.OptionContent
import com.example.phonepeandroid.usecase.MainUseCase
import com.example.phonepeandroid.view.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val dispatcher by lazy { Dispatchers.IO }
    private val mainUseCase by lazy { MainUseCase(MainRepositoryImpl()) }

    private val _uiStateLiveData = MutableLiveData<UiState<List<OptionContent>>>(UiState.LOADING)
    val uiStateLiveData : LiveData<UiState<List<OptionContent>>> = _uiStateLiveData

    fun fetchOptions() {
        viewModelScope.launch(dispatcher) {
            mainUseCase.getOptions().collect {
                withContext(Dispatchers.Main) {
                    _uiStateLiveData.value = it
                }
            }
        }
    }
}