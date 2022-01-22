package com.shiraj.data.repository

import com.shiraj.data.local.QuestionDao
import com.shiraj.data.model.Question
import com.shiraj.data.remote.GoodWallRemoteDataSource
import com.shiraj.domain.repository.GoodWallRepository
import com.shiraj.domain.model.Output
import com.shiraj.domain.model.QuestionEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GoodWallRepositoryImpl @Inject constructor(
    private val goodWallRemoteDataSource: GoodWallRemoteDataSource,
    private val questionDao: QuestionDao
) : GoodWallRepository {

    override suspend fun fetchQuestions(): Flow<Output<Boolean>?> {
        return flow {
            emit(Output.loading())
            val result = goodWallRemoteDataSource.fetchQuestions()

            //Cache to database if response is successful
            if (result.status == Output.Status.SUCCESS) {
                result.data?.let { it ->
                    questionDao.deleteAll()
                    questionDao.insertAll(it)
                }
            }
            emit(result.toBooleanResult())
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun fetchQuestionsCached(): Flow<Output<List<QuestionEntity>>> {
        return flow {
            val result = getLocalQuestions()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    private fun getLocalQuestions() = Output.success(questionDao.getQuestions())

    private fun Output<List<Question>?>.toBooleanResult(): Output<Boolean> {
        return Output(
            status = status,
            data = !data.isNullOrEmpty(),
            error = error,
            message = message
        )
    }

}