package com.example.domain.features.category.repository

import com.example.domain.common.Resource
import com.example.domain.features.category.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    suspend fun getCategories(): Flow<Resource<List<Category>>>
}