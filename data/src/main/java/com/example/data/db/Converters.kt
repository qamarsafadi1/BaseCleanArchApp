package com.example.data.db


import androidx.room.TypeConverter
import com.example.data.db.categories.entity.Brands
import com.example.data.db.categories.entity.CategoryEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {

    @TypeConverter
    fun fromCategoryList(images: MutableList<CategoryEntity?>?): String? {
        if (images == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<MutableList<CategoryEntity?>?>() {}.type
        return gson.toJson(images, type)
    }

    @TypeConverter
    fun toCategoryList(images: String?): MutableList<CategoryEntity>? {
        if (images == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<MutableList<CategoryEntity?>?>() {}.type
        return gson.fromJson(images, type)
    }

    @TypeConverter
    fun fromCategoryArrayList(images: ArrayList<CategoryEntity?>?): String? {
        if (images == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<CategoryEntity?>?>() {}.type
        return gson.toJson(images, type)
    }

    @TypeConverter
    fun toCategoryArrayList(images: String?): ArrayList<CategoryEntity>? {
        if (images == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<CategoryEntity?>?>() {}.type
        return gson.fromJson(images, type)
    }

    @TypeConverter
    fun fromBrandList(images: MutableList<Brands?>?): String? {
        if (images == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<MutableList<Brands?>?>() {}.type
        return gson.toJson(images, type)
    }

    @TypeConverter
    fun toBrandList(images: String?): MutableList<Brands>? {
        if (images == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<MutableList<Brands?>?>() {}.type
        return gson.fromJson(images, type)
    }

}