package com.example.basecleanarchapp.ui.home.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.basecleanarchapp.base.Event
import com.example.basecleanarchapp.base.Resource
import com.example.basecleanarchapp.ui.home.mapper.CategoriesMapper
import com.example.basecleanarchapp.ui.home.model.*
import com.example.domain.common.Status
import com.example.domain.features.category.usecase.GetCategoriesUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CategoriesViewModel(
    app: Application,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val mapper: CategoriesMapper
) : AndroidViewModel(app) {

    private val _categories =
        MutableStateFlow<Event<Resource<List<Category>>>>(Event(Resource.init(null)))
    val remoteCategories: StateFlow<Event<Resource<List<Category>>>> = _categories

    fun getCategories() {
        viewModelScope.launch {
            _categories.value = Event(Resource.loading(null))
            val categoriesResult = getCategoriesUseCase.getCategories()
            categoriesResult.collect { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        result.data?.let {
                            _categories.value =
                                Event(Resource.success(mapper.fromCategoryDomainToPresenter(it)))
                        }
                    }
                    Status.ERROR -> {
                        _categories.value = Event(
                            Resource.error(
                                mutableListOf(),
                                result.message,
                                result.errors
                            )
                        )
                    }
                    else -> {
                        _categories.value = Event(
                            Resource.loading(
                                null
                            )
                        )
                    }
                }
            }
        }
    }

    class CategoryViewModelFactory(
        val app: Application,
        private val getCategoriesUseCase: GetCategoriesUseCase,
        private val mapper: CategoriesMapper
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CategoriesViewModel(
                app,
                getCategoriesUseCase,
                mapper
            ) as T
        }
    }
}