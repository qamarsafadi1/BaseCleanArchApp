package com.example.domain.features.category.model

data class CatergoriesResponse(
    val categories: List<Category> = listOf(),
    val response_message: String = "",
    val status: Boolean = false
)