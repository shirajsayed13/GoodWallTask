package com.shiraj.goodwalltask.questions

import com.shiraj.domain.model.Output
import com.shiraj.domain.usecase.QuestionUseCase
import com.shiraj.goodwalltask.BaseViewModelTest
import com.shiraj.goodwalltask.getDummyQuestionsEntity
import com.shiraj.goodwalltask.runBlockingMainTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class QuestionsViewModelTest : BaseViewModelTest() {

    @Mock
    private lateinit var questionUseCase: QuestionUseCase

    private lateinit var questionsViewModel: QuestionsViewModel

    @Before
    fun setup() {
        questionsViewModel = QuestionsViewModel(questionUseCase)
    }

    @Test
    fun `Given Questions When fetchQuestions should return Success`() = runBlockingMainTest {
        //GIVEN
        val flowQuestions = flowOf(Output.success(getDummyQuestionsEntity()))

        //WHEN
        Mockito.doReturn(flowQuestions).`when`(questionUseCase).fetchQuestionsCached()
        questionsViewModel.fetchQuestionsCached()

        //THEN
        Assert.assertEquals(1, questionsViewModel.questionList.value?.data?.size)
    }
}