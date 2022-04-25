package com.example.data.db.categories.mapper

import com.example.domain.features.category.model.Brands

class BrandMapper {
    fun toBrand(brands: Brands): com.example.data.db.categories.entity.Brands {
        return com.example.data.db.categories.entity.Brands(
            id = brands.id,
            name = brands.name,
            icon = brands.icon,
            status = brands.status
        )
    }

    fun fromBrand(brands: com.example.data.db.categories.entity.Brands): Brands {
        return Brands(
            id = brands.id,
            name = brands.name,
            icon = brands.icon,
            status = brands.status
        )
    }
}