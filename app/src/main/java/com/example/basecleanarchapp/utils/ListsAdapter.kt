package com.example.basecleanarchapp.utils

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.basecleanarchapp.R
import com.example.basecleanarchapp.base.Adapter
import com.example.basecleanarchapp.databinding.CategoryItemBinding
import com.example.basecleanarchapp.ui.home.model.Category
import io.jmdg.spanly.size

@BindingAdapter(value = ["list", "onClick"])
fun initCategoriesList(
    view: RecyclerView,
    list: MutableList<Category>? = mutableListOf(),
    onClick: () -> Unit,
): Adapter<Category> {
    var adapter: Adapter<Category>? = null
    adapter = Adapter(
        R.layout.category_item,
        onClick = { position: Int, category: Category ->
            onClick()
        },
        onBind = { position: Int, item: Category, view: View, bind: ViewDataBinding ->
            val binding = bind as CategoryItemBinding
            with(binding) {
                category = item
            }
        }
    )
    view.adapter = adapter
    adapter.items = list?.toMutableList() ?: mutableListOf()
    return adapter
}
