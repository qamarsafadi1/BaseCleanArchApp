package com.example.basecleanarchapp


import android.app.Application
import com.example.basecleanarchapp.ui.home.CategorySL
import com.example.basecleanarchapp.ui.home.mapper.CategoriesMapper
import com.example.data.repository.categories.CategoriesRepositoryImpl
import com.example.domain.features.category.usecase.GetCategoriesUseCase

class BaseApplication : Application() {
    private val categoriesRepository: CategoriesRepositoryImpl
        get() = CategorySL.provideCategoriesRepository(this)

    val getCategoriesUseCase: GetCategoriesUseCase
        get() = GetCategoriesUseCase(categoriesRepository)

    val categoriesMapper = CategoriesMapper()

    override fun onCreate() {
        super.onCreate()
    }
}