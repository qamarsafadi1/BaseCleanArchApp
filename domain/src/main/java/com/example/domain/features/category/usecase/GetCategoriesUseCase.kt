package com.example.domain.features.category.usecase

import com.example.domain.features.category.repository.CategoriesRepository

class GetCategoriesUseCase(private val categoriesRepository: CategoriesRepository) {

    suspend fun getCategories() = categoriesRepository.getCategories()

}