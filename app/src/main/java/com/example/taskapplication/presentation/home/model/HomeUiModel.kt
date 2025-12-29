package com.example.taskapplication.presentation.home.model

data class TopServices(
    val image: Int?=null,
    val serviceName: String?=null
)

data class AcServices(
    val image: Int?=null,
    val serviceName: String?=null,
    val rating: String?=null,
    val totalReviews: String?=null
)
