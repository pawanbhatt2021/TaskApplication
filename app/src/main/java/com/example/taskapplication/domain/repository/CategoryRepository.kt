package com.example.taskapplication.domain.repository

import com.example.taskapplication.domain.model.ApiResult
import com.example.taskapplication.domain.model.Category


interface CategoryRepository {
    suspend fun getCategory(): ApiResult<List<Category>>
}