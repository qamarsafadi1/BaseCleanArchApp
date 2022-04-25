package com.example.data.db.categories.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryEntity(
    val cover: String = "",
    val icon: String = "",
    @PrimaryKey
    val id: Int = 0,
    var name: String = "",
    val parent_id: Int = 0,
    val status: String = "",
    val children: ArrayList<CategoryEntity> = arrayListOf<CategoryEntity>(),
    val brand: List<Brands> = listOf(),
)