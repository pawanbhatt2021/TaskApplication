package com.example.taskapplication.domain.model


import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

interface SafeApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): ApiResult<T> {
        return try {
            ApiResult.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            val errorMsg = when (throwable) {
                is UnknownHostException -> "No Internet Connection"
                is SocketTimeoutException -> "Request Timed Out"
                is HttpException -> "Server Error: ${throwable.code()}"
                else -> throwable.localizedMessage ?: "Something went wrong"
            }
            ApiResult.Error(errorMsg, throwable)
        }
    }
}
