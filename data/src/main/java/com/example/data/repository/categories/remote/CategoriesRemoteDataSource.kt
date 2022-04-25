package com.example.data.repository.categories.remote

import com.example.domain.common.Resource
import com.example.domain.features.category.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoriesRemoteDataSource {
    suspend fun getCategories(): Flow<Resource<List<Category>>>
}