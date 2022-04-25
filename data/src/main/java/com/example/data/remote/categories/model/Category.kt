package com.example.data.remote.categories.model

import com.google.gson.annotations.SerializedName

data class Category(
    val cover: String = "",
    val icon: String = "",
    val id: Int = 0,
    var name: String = "",
    @SerializedName("parent_id")
    val parentId: Int = 0,
    val status: String = "",
    val children: ArrayList<Category> = arrayListOf<Category>(),
    val brand: List<Brands> = listOf(),
)