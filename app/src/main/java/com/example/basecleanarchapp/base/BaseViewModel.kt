package com.example.basecleanarchapp.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BaseViewModel<in U, in A>(val app: Application,
                                private val getUseCase: U,
                                private val mapper: A) : AndroidViewModel(app) {

    class ViewModelFactory<in U, in A>(
        val app: Application,
        private val getCategoriesUseCase: U,
        private val mapper: A
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return BaseViewModel(
                app,
                getCategoriesUseCase,
                mapper
            ) as T
        }
    }
}