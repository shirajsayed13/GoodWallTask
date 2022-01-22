package com.shiraj.domain.repository

import com.shiraj.domain.model.Output
import com.shiraj.domain.model.QuestionEntity
import kotlinx.coroutines.flow.Flow

interface GoodWallRepository {
    suspend fun fetchQuestions(): Flow<Output<Boolean>?>
    suspend fun fetchQuestionsCached(): Flow<Output<List<QuestionEntity>>>
}