package com.shiraj.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shiraj.data.getDummyQuestions
import com.shiraj.data.getDummyQuestionsEntity
import com.shiraj.data.local.QuestionDao
import com.shiraj.data.remote.GoodWallRemoteDataSource
import com.shiraj.domain.model.Output
import com.shiraj.domain.repository.GoodWallRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GoodWallRepositoryTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var goodWallRepository: GoodWallRepository

    @Mock
    lateinit var goodWallRemoteDataSource: GoodWallRemoteDataSource

    @Mock
    lateinit var questionDao: QuestionDao

    @Before
    fun setup() {
        goodWallRepository = GoodWallRepositoryImpl(goodWallRemoteDataSource, questionDao)
    }

    @Test
    fun `Given Questions When fetchQuestions returns Success`() = runBlocking {
        //GIVEN
        val givenQuestions = getDummyQuestions()
        val givenQuestionsOutput = Output.success(givenQuestions)
        val inputFlow = listOf(Output.loading(), Output.success(true))
        `when`(goodWallRemoteDataSource.fetchQuestions()).thenReturn(givenQuestionsOutput)

        //WHEN
        val outputFlow = goodWallRepository.fetchQuestions().toList()

        //THEN
        assert(outputFlow.size == 2)
        assert(inputFlow[0] == outputFlow[0])
        assert(inputFlow[1] == outputFlow[1])
    }

    @Test
    fun `Given Questions When fetchQuestionsCached returns Success`() = runBlocking {
        //GIVEN
        val givenQuestions = getDummyQuestionsEntity()
        `when`(questionDao.getQuestions()).thenReturn(givenQuestions)

        //WHEN
        val outputFlow = goodWallRepository.fetchQuestionsCached().toList()

        //THEN
        assert(outputFlow.size == 1)
    }

}