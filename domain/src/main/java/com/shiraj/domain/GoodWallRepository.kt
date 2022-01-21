package com.shiraj.domain

import kotlinx.coroutines.flow.Flow

interface GoodWallRepository {
    suspend fun fetchQuestions(): Flow<Output<Boolean>>
    suspend fun fetchQuestionsCached(): Flow<Output<List<QuestionEntity>>>
}