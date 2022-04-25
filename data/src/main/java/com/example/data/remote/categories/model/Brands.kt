package com.example.data.remote.categories.model

import com.google.gson.annotations.SerializedName


data class Brands(
    val category: Category = Category(),
    @SerializedName("category_id")
    val categoryId: Int = 0,
    val description: String = "",
    val icon: String = "",
    val id: Int = 0,
    val name: String = "",
    val status: String = "",
    var showSection: Boolean = false
)