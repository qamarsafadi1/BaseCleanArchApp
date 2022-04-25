package com.example.data.remote.categories.model

import com.google.gson.annotations.SerializedName

data class CatergoriesResponse(
    val categories: List<Category> = listOf(),
    @SerializedName("response_message")
    val responseMessage: String = "",
    val status: Boolean = false
)