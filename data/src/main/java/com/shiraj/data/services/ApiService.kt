package com.shiraj.data.services

import com.shiraj.data.model.Question
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("Android-Interview-Question/questions.json")
    suspend fun getQuestions(): Response<List<Question>>
}