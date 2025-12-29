package com.example.taskapplication.data.remote.source

import com.example.taskapplication.data.remote.api.HomeApi
import javax.inject.Inject


class CategoriesRemoteSource @Inject constructor(private val api: HomeApi) {
    suspend fun getCategories() = api.getCategories()
}