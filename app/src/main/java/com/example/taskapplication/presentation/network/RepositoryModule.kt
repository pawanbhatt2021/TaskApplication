package com.example.taskapplication.presentation.network

import com.example.taskapplication.data.repository.CategoryRepositoryImpl
import com.example.taskapplication.domain.repository.CategoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCategoryRepository(
        impl: CategoryRepositoryImpl
    ): CategoryRepository
}
