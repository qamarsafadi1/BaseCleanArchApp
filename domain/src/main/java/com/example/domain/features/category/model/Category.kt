package com.example.domain.features.category.model

data class Category(
    val cover: String = "",
    val icon: String = "",
    val id: Int = 0,
    var name: String = "",
    val parent_id: Int = 0,
    val status: String = "",
    val children: ArrayList<Category> = arrayListOf<Category>(),
    val brand: List<Brands> = listOf(),
)