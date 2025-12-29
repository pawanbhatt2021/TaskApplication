package com.example.taskapplication.data.remote.api

import com.example.taskapplication.data.remote.dto.BaseResponse
import com.example.taskapplication.domain.model.Category
import com.example.taskapplication.presentation.common.constant.Constants
import retrofit2.http.GET

interface HomeApi {
    @GET(Constants.ApiEndPoint.root)
    suspend fun getCategories():BaseResponse<List<Category>>
}