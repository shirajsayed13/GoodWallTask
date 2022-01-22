package com.shiraj.data

import com.shiraj.data.model.Question
import com.shiraj.data.services.ApiService
import com.shiraj.domain.model.Output
import retrofit2.Retrofit
import javax.inject.Inject

class GoodWallRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    private val retrofit: Retrofit
) : BaseRemoteDataSource(retrofit) {

    suspend fun fetchQuestions(): Output<List<Question>?> {
        return getResponse(
            request = { apiService.getQuestions() },
            defaultErrorMessage = "Error Fetching Questions"
        )
    }
}