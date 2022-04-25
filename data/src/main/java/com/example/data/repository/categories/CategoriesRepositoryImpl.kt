package com.example.data.repository.categories

import android.content.Context
import android.util.Log
import com.example.data.repository.categories.local.CategoriesLocalDataSource
import com.example.data.repository.categories.remote.CategoriesRemoteDataSource
import com.example.data.util.Common.Companion.haveNetworkConnection
import com.example.domain.common.Resource
import com.example.domain.features.category.model.Category
import com.example.domain.features.category.repository.CategoriesRepository
import kotlinx.coroutines.flow.Flow

class CategoriesRepositoryImpl(
    private val context: Context,
    private val localDataSource: CategoriesLocalDataSource,
    private val remoteDataSource: CategoriesRemoteDataSource
) : CategoriesRepository {

    override suspend fun getCategories(): Flow<Resource<List<Category>>> {
        return remoteDataSource.getCategories()

    }

}