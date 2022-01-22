package com.shiraj.domain.usecase

import com.shiraj.domain.GoodWallRepository
import com.shiraj.domain.model.Output
import com.shiraj.domain.model.QuestionEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuestionUseCaseImpl @Inject constructor(
    private val goodWallRepository: GoodWallRepository
) : QuestionUseCase {

    override suspend fun fetchQuestions(): Flow<Output<Boolean>?> {
        return goodWallRepository.fetchQuestions()
    }

    override suspend fun fetchQuestionsCached(): Flow<Output<List<QuestionEntity>>> {
        return goodWallRepository.fetchQuestionsCached()
    }
}