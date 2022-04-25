package com.example.data.remote.categories.service


import com.example.data.remote.categories.model.CatergoriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.QueryMap


interface CategoriesService {
    @GET("v1/app/category/get_categories")
    @JvmSuppressWildcards
    suspend fun getCategories(
        @HeaderMap
        header: Map<String, Any>,
        @QueryMap
        body: Map<String, Any>
    ): Response<CatergoriesResponse>


}