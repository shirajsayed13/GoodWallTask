package com.shiraj.domain

data class ApiError(
    val statusCode: Int = 0,
    val statusMessage: String? = null
)
