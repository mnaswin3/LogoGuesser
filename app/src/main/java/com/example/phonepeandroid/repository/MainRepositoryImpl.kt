package com.example.phonepeandroid.repository

import android.content.Context
import com.example.phonepeandroid.repository.modal.Option
import com.example.phonepeandroid.utils.JsonReader
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class MainRepositoryImpl : MainRepositoryApi {

    override fun fetchOptions(): NetworkResult<List<Option>> {
        try {
//            val jsonString = JsonReader.loadJSONFromAsset(context, "response.json")
//            val options = JsonReader.parseJsonToOptionList(jsonString!!)!!
//            emit(NetworkResult.SUCCESS(options))

            return NetworkResult.SUCCESS(listOf(
                Option(
                    name = "AADHAAR",
                    imgUrl = "http://www.dsource.in/sites/default/files/resource/logo-design/logos/logos-representing-india/images/01.jpeg"
                ),
                Option(
                    name = "PHONEPE",
                    imgUrl = "https://static.digit.in/default/thumb_101067_default_td_480x480.jpeg"
                )
            ))
        } catch (e: Exception) {
            return NetworkResult.ERROR(Exception("Network exception"))
        }
    }
}