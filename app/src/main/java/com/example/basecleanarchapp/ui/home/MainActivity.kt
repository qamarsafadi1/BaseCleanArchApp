package com.example.basecleanarchapp.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.basecleanarchapp.BaseApplication
import com.example.basecleanarchapp.base.Adapter
import com.example.basecleanarchapp.databinding.ActivityMainBinding
import com.example.basecleanarchapp.ui.home.model.Category
import com.example.basecleanarchapp.ui.home.viewmodels.CategoriesViewModel
import com.example.basecleanarchapp.utils.Common
import com.example.basecleanarchapp.utils.initCategoriesList
import com.example.basecleanarchapp.base.Status
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: Adapter<Category>
    private val viewModel: CategoriesViewModel by viewModels {
        CategoriesViewModel.CategoryViewModelFactory(
            application,
            (application as BaseApplication).getCategoriesUseCase,
            (application as BaseApplication).categoriesMapper,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        with(binding){
            setContentView(root)
            adapter = initCategoriesList(categoryList){}
        }
        viewModel.getCategories()
        subscribeObservers()
    }

    private fun subscribeObservers() {
       lifecycleScope.launch {
           repeatOnLifecycle(Lifecycle.State.CREATED){
               viewModel.remoteCategories.collect{
                   val resp = it.peekContent()
                   when (resp.status) {
                       Status.LOADING -> {
                       }
                       Status.SUCCESS -> {
                           adapter.items = resp.data?.toMutableList() ?: mutableListOf()
                       }
                       Status.ERROR -> {
                          Common.handleErrors(resp.message, resp.errors, this@MainActivity)
                       }
                   }
               }
           }
       }
    }
}