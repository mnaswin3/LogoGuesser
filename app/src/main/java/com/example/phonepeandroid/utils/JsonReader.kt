package com.example.phonepeandroid.utils

import android.content.Context
import com.example.phonepeandroid.repository.modal.Option
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.nio.charset.StandardCharsets

class JsonReader {
    companion object {
        fun loadJSONFromAsset(context: Context, filename: String): String? {
            var json: String? = null
            try {
                val inputStream = context.assets.open(filename)
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                json = String(buffer, StandardCharsets.UTF_8)
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
            return json
        }

        fun parseJsonToOptionList(json: String): List<Option>? {
            return try {
                val gson = Gson()
                val type = object : TypeToken<List<Option>>() {}.type
                gson.fromJson(json, type)
            } catch (ex: Exception) {
                ex.printStackTrace()
                null
            }
        }
    }
}