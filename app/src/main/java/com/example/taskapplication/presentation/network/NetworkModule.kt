package com.example.taskapplication.presentation.network

import com.example.taskapplication.data.remote.api.HomeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
     fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(null)
            .connectTimeout(NetworkConstants.TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(NetworkConstants.TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(NetworkConstants.TIME_OUT, TimeUnit.SECONDS)
            .build()
    }
    @Provides
    @Singleton
     fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .client(okHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCategoryApi(retrofit: Retrofit): HomeApi =
        retrofit.create(HomeApi::class.java)
}