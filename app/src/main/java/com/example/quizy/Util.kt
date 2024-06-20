package com.example.quizy

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStreamReader

fun Context.loadJsonFromAsset(fileName: String): String? {
    return try {
        val inputStream = assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        String(buffer, Charsets.UTF_8)
    } catch (ex: IOException) {
        ex.printStackTrace()
        null
    }
}

fun parseQuizJson(json: String): Quiz? {
    val quizType = object : TypeToken<Quiz>() {}.type
    return Gson().fromJson(json, quizType)
}

fun readJsonFromAssets(context: Context, fileName: String): String? {
    return try {
        val inputStream = context.assets.open(fileName)
        val reader = InputStreamReader(inputStream)
        reader.readText().also {
            reader.close()
            inputStream.close()
        }
    } catch (ex: IOException) {
        ex.printStackTrace()
        null
    }
}

