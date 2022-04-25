package com.example.data.db.categories.dao


import androidx.room.*
import com.example.data.db.categories.entity.CategoryEntity

@Dao
interface CategoriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: CategoryEntity)

    @Query("SELECT * FROM category")
    fun getCategories(): List<CategoryEntity>
}