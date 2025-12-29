package com.example.taskapplication.data.remote.dto

import com.google.gson.JsonArray


data class BaseResponse<T>(
    val status: Int,
    val data: T?,
    val error: String?
)
