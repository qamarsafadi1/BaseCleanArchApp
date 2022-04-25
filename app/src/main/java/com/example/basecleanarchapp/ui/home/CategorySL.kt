package com.example.basecleanarchapp.ui.home

import android.annotation.SuppressLint
import android.content.Context
import com.example.data.remote.RetrofitClient
import com.example.data.remote.categories.service.CategoriesService
import com.example.data.db.AppDatabase
import com.example.data.db.categories.mapper.BrandMapper
import com.example.data.db.categories.mapper.CategoryEntityMapper
import com.example.data.remote.categories.mapper.CategoryApiResponseMapper
import com.example.data.repository.categories.CategoriesRepositoryImpl
import com.example.data.repository.categories.local.CategoriesLocalDataSource
import com.example.data.repository.categories.local.CategoriesLocalDataSourceImp
import com.example.data.repository.categories.remote.CategoriesRemoteDataSourceImpl
import kotlinx.coroutines.Dispatchers

/*
                        The service locator pattern provides a registry
            where classes can obtain their dependencies instead of constructing them.
*/

object CategorySL {

    private var database: AppDatabase? = null
    private val categoryEntityMapper by lazy {
        CategoryEntityMapper(BrandMapper())
    }

    @SuppressLint("StaticFieldLeak")
    @Volatile
    var categoriesRepository: CategoriesRepositoryImpl? = null

    fun provideCategoriesRepository(context: Context): CategoriesRepositoryImpl {
        // useful because this method can be accessed by multiple threads
        synchronized(this) {
            return categoriesRepository ?: createCategoriesRepository(context)
        }
    }

    private fun createCategoriesRepository(context: Context): CategoriesRepositoryImpl {
        val database = database ?: createDataBase(context)
        val newRepo =
            CategoriesRepositoryImpl(
                context,
                createCategoriesLocalDataSource(context),
                CategoriesRemoteDataSourceImpl(
                    RetrofitClient.getService(CategoriesService::class.java),
                    CategoriesLocalDataSourceImp(
                        database.appDao(),
                        Dispatchers.IO,
                        categoryEntityMapper
                    ), CategoryApiResponseMapper()
                )
            )
        categoriesRepository = newRepo
        return newRepo
    }

    private fun createCategoriesLocalDataSource(context: Context): CategoriesLocalDataSource {
        val database = database ?: createDataBase(context)
        return CategoriesLocalDataSourceImp(
            database.appDao(),
            Dispatchers.IO,
            categoryEntityMapper
        )
    }

    private fun createDataBase(context: Context): AppDatabase {
        val result = AppDatabase.getInstance(context)
        database = result
        return result
    }
}