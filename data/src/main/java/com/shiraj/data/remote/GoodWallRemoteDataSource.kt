package com.shiraj.data.remote

import com.shiraj.data.BaseRemoteDataSource
import com.shiraj.data.model.Question
import com.shiraj.data.services.ApiService
import com.shiraj.domain.model.Output
import retrofit2.Retrofit
import javax.inject.Inject

class GoodWallRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    retrofit: Retrofit
) : BaseRemoteDataSource(retrofit) {

    suspend fun fetchQuestions(): Output<List<Question>?> {
        return getResponse(
            request = { apiService.getQuestions() },
            defaultErrorMessage = "Error Fetching Questions"
        )
    }
}