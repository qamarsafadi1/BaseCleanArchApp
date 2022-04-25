package com.example.basecleanarchapp.ui.home.model


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