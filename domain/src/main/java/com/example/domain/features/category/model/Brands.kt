package com.example.domain.features.category.model

data class Brands(
    val category: Category = Category(),
    val category_id: Int = 0,
    val description: String = "",
    val icon: String = "",
    val id: Int = 0,
    val name: String = "",
    val status: String = "",
    var showSection: Boolean = false
)