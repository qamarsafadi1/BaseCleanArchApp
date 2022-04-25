package com.example.basecleanarchapp.ui.home.mapper

import com.example.domain.features.category.model.Category

class CategoriesMapper {
    fun fromCategoryDomainToPresenter(
        categories: List<Category>
    ): List<com.example.basecleanarchapp.ui.home.model.Category> {
        return categories.map {
            com.example.basecleanarchapp.ui.home.model.Category(
                id = it.id,
                name = it.name,
                cover = it.cover,
                icon = it.icon
            )
        }
    }
}