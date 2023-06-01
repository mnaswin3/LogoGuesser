package com.example.phonepeandroid.repository

import android.content.Context
import com.example.phonepeandroid.repository.modal.Option
import kotlinx.coroutines.flow.Flow

interface MainRepositoryApi {

    fun fetchOptions() : NetworkResult<List<Option>>
}