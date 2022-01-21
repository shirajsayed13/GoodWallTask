package com.shiraj.data

import com.shiraj.domain.GoodWallRepository
import com.shiraj.domain.Output
import com.shiraj.domain.QuestionEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GoodWallRepositoryImpl @Inject constructor(
    private val goodWallRemoteDataSource: GoodWallRemoteDataSource
) : GoodWallRepository {

    override suspend fun fetchQuestions(): Flow<Output<Boolean>> {
        return flow {
            emit(Output.loading())
            val result = goodWallRemoteDataSource.fetchQuestions()

        }
    }

    override suspend fun fetchQuestionsCached(): Flow<Output<List<QuestionEntity>>> {
        TODO("Not yet implemented")
    }

}