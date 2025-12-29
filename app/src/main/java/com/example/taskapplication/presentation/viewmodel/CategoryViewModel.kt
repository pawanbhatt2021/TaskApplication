package com.example.taskapplication.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapplication.domain.model.ApiResult
import com.example.taskapplication.domain.model.Category
import com.example.taskapplication.domain.useCase.GetCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getCategoryUseCase: GetCategoryUseCase
) : ViewModel() {

    private val _categories = MutableLiveData<ApiResult<List<Category>>>()
    val categories: LiveData<ApiResult<List<Category>>>
        get() =  _categories

    fun loadCategories() {
        viewModelScope.launch {
            _categories.value = ApiResult.Loading
            val result = getCategoryUseCase()
            Log.e("pawanLogs","")
            _categories.value = result
        }
    }
}
