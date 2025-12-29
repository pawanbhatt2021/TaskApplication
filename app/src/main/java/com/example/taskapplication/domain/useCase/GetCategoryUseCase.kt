package com.example.taskapplication.domain.useCase

import com.example.taskapplication.domain.model.ApiResult
import com.example.taskapplication.domain.model.Category
import com.example.taskapplication.domain.repository.CategoryRepository
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(private val repository: CategoryRepository) {
    suspend operator fun invoke():ApiResult<List<Category>>  = repository.getCategory()
}