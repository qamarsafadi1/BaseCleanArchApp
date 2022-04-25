package com.example.data.repository.categories.remote

import com.example.data.remote.categories.service.CategoriesService
import com.example.data.remote.categories.mapper.CategoryApiResponseMapper
import com.example.data.repository.categories.local.CategoriesLocalDataSourceImp
import com.example.domain.common.Resource
import com.example.domain.features.category.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

class CategoriesRemoteDataSourceImpl(
    private val service: CategoriesService,
    private var categoriesLocalDataSourceImp: CategoriesLocalDataSourceImp,
    private val mapper: CategoryApiResponseMapper
) : CategoriesRemoteDataSource {

    override suspend fun getCategories(): Flow<Resource<List<Category>>> =
        withContext(Dispatchers.IO) {
            val data: Flow<Resource<List<Category>>> = try {
                val headers = HashMap<String, String>()
                val body = HashMap<String, Any>()
                val response = service.getCategories(headers, body)
                if (response.isSuccessful) {
                    mapper.toCategoryList(response.body()!!).map {
                        categoriesLocalDataSourceImp.insert(it)
                    }
                    MutableStateFlow(Resource.success(categoriesLocalDataSourceImp.getCategories()))
                } else {
                    MutableStateFlow(Resource.error(null, response.message(), null))
                }
            } catch (e: Exception) {
                MutableStateFlow(Resource.error(null, e.message, null))
            }
            data
        }
}