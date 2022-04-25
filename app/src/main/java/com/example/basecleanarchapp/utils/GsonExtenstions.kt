package com.example.basecleanarchapp.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object GsonUtil {

    val gson by lazy { Gson() }

    fun toJson(obj: Any): String {
        return gson.toJson(obj)
    }

    inline fun <reified T> fromJson(json: String): T {
        return gson.fromJsonTypeToken(json)
    }

}


inline fun <reified T> Gson.fromJsonTypeToken(json: String) =
    this.fromJson<T>(json, object : TypeToken<T>() {}.type)

fun Any.toJson() = GsonUtil.toJson(this)

inline fun <reified T> String.fromJson() = GsonUtil.fromJson<T>(this)