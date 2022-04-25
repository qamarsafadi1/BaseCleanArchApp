package com.example.data.db.categories.entity

data class Brands(
    val category: CategoryEntity = CategoryEntity(),
    val category_id: Int = 0,
    val description: String = "",
    val icon: String = "",
    val id: Int = 0,
    val name: String = "",
    val status: String = "",
    var showSection: Boolean = false
)