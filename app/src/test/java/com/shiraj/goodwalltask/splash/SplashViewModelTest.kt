package com.shiraj.goodwalltask.splash

import com.shiraj.domain.model.Output
import com.shiraj.domain.usecase.QuestionUseCase
import com.shiraj.goodwalltask.BaseViewModelTest
import com.shiraj.goodwalltask.runBlockingMainTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SplashViewModelTest : BaseViewModelTest() {

    @Mock
    private lateinit var questionUseCase: QuestionUseCase

    private lateinit var splashViewModel: SplashViewModel

    @Before
    fun setup() {
        splashViewModel = SplashViewModel(questionUseCase)
    }

    @Test
    fun `Given questions When fetchQuestions return Success`() = runBlockingMainTest {
        //GIVEN
        val flowQuestions = flowOf(Output.success(true))

        //WHEN
        Mockito.doReturn(flowQuestions).`when`(questionUseCase).fetchQuestions()
        splashViewModel.fetchQuestions()

        //THEN
        assert(splashViewModel.questionOk.value?.data == true)
    }
}