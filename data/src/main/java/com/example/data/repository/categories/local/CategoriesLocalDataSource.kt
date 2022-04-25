package com.example.data.repository.categories.local

import com.example.domain.common.Resource
import com.example.domain.features.category.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoriesLocalDataSource {
    suspend fun insert(category: Category)
    suspend fun getCategories(): List<Category>
}