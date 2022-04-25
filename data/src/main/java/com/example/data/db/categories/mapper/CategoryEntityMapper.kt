package com.example.data.db.categories.mapper


import com.example.data.db.categories.entity.CategoryEntity
import com.example.domain.features.category.model.Category

class CategoryEntityMapper(val brandMapper: BrandMapper) {

    fun toCategoryEntity(category: Category): CategoryEntity {
        return CategoryEntity(
            id = category.id,
            name = category.name,
            cover = category.cover,
            icon = category.icon,
            parent_id = category.parent_id,
            status = category.status,
            brand = category.brand.map { brandMapper.toBrand(it) }
        )
    }

    fun toCategory(categoryEntity: CategoryEntity): Category {
        return Category(
            id = categoryEntity.id,
            name = categoryEntity.name,
            cover = categoryEntity.cover,
            icon = categoryEntity.icon,
            parent_id = categoryEntity.parent_id,
            status = categoryEntity.status,
            brand = categoryEntity.brand.map { brandMapper.fromBrand(it) }
        )
    }
}