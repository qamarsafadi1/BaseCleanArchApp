package com.example.data.repository.categories.local

import com.example.data.db.categories.dao.CategoriesDao
import com.example.data.db.categories.mapper.CategoryEntityMapper
import com.example.domain.features.category.model.Category
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoriesLocalDataSourceImp(
    private val categoriesDao: CategoriesDao,
    private val dispatcher: CoroutineDispatcher,
    private val categoryEntityMapper: CategoryEntityMapper
) : CategoriesLocalDataSource {

    override suspend fun insert(category: Category) = withContext(dispatcher) {
        categoriesDao.insert(categoryEntityMapper.toCategoryEntity(category))
    }

    override suspend fun getCategories(): List<Category> = withContext(
        Dispatchers.IO
    ) {
        categoriesDao.getCategories().map {
            categoryEntityMapper.toCategory(it)
        }
    }
}
