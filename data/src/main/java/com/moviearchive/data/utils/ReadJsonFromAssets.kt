package com.moviearchive.data.utils

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class ReadJsonFromAssets(val contex: Context) {

    fun <T> readjson(path: String): List<T> {

        lateinit var jsonString: String
        try {
            jsonString = contex.assets.open("$path")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            Log.e("error", "message : $ioException")
        }
        val listType = object : TypeToken<List<T>>() {}.type
        return Gson().fromJson(jsonString, listType)


    }

}

