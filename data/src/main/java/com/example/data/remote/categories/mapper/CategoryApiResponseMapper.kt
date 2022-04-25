package com.example.data.remote.categories.mapper

import com.example.data.remote.categories.model.CatergoriesResponse
import com.example.domain.features.category.model.Brands
import com.example.domain.features.category.model.Category


class CategoryApiResponseMapper {
    fun toCategoryList(response: CatergoriesResponse): List<Category> {
        return response.categories.map {
            Category(
                id = it.id,
                name = it.name,
                cover = it.cover,
                icon = it.icon,
                brand = it.brand.map { toBrandList(it) }
            )
        }
    }

    private fun toBrandList(response: com.example.data.remote.categories.model.Brands): Brands {
        return Brands(
                id = response.id,
                name = response.name,
                icon = response.icon
            )
        }

}