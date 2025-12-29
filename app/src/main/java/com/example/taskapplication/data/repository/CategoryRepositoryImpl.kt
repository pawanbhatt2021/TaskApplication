package com.example.taskapplication.data.repository

import com.example.taskapplication.data.remote.source.CategoriesRemoteSource
import com.example.taskapplication.domain.model.ApiResult
import com.example.taskapplication.domain.model.Category
import com.example.taskapplication.domain.model.SafeApiCall
import com.example.taskapplication.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl  @Inject constructor(
    private val remote: CategoriesRemoteSource
) : CategoryRepository, SafeApiCall {

    override suspend fun getCategory(): ApiResult<List<Category>> {
        return safeApiCall {
                val response = remote.getCategories()
            if (response.status == 1) {
                response.data ?: emptyList()
            } else {
                throw Exception(response.error ?: "Unknown Error")
            }

        }
        }
}
