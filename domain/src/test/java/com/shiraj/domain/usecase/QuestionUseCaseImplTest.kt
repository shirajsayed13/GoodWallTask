package com.shiraj.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shiraj.domain.getDummyQuestionsEntity
import com.shiraj.domain.model.Output
import com.shiraj.domain.repository.GoodWallRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class QuestionUseCaseImplTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var goodWallRepository: GoodWallRepository
    private lateinit var questionUseCase: QuestionUseCaseImpl

    @Before
    fun setup() {
        questionUseCase = QuestionUseCaseImpl(goodWallRepository)
    }

    @Test
    fun `Given Questions When UseCase fetchQuestions return Success`() = runBlocking {
        //GIVEN
        val inputFlow = flowOf(Output.success(true))
        Mockito.`when`(goodWallRepository.fetchQuestions()).thenReturn(inputFlow)

        //WHEN
        val output = questionUseCase.fetchQuestions().toList()

        //THEN
        assert(output[0]?.data == true)
    }

    @Test
    fun `Given Questions When UseCase fetchQuestionsCached returns Success`() = runBlocking {
        //GIVEN
        val givenQuestions = flowOf(Output.success(getDummyQuestionsEntity()))
        Mockito.`when`(goodWallRepository.fetchQuestionsCached()).thenReturn(givenQuestions)

        //WHEN
        val outputFlow = questionUseCase.fetchQuestionsCached().toList()

        //THEN
        assert(outputFlow.size == 1)
    }

}