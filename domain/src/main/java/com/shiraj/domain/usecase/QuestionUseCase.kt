package com.shiraj.domain.usecase

import com.shiraj.domain.model.Output
import com.shiraj.domain.model.QuestionEntity
import kotlinx.coroutines.flow.Flow


interface QuestionUseCase {
    suspend fun fetchQuestions(): Flow<Output<Boolean>?>
    suspend fun fetchQuestionsCached(): Flow<Output<List<QuestionEntity>>>
}