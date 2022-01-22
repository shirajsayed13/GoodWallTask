package com.shiraj.goodwalltask.questions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shiraj.domain.model.Output
import com.shiraj.domain.model.QuestionEntity
import com.shiraj.domain.usecase.QuestionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val questionUseCase: QuestionUseCase
) : ViewModel() {

    private val _questionList = MutableLiveData<Output<List<QuestionEntity>>>()
    val questionList = _questionList

    fun fetchQuestionsCached() {
        viewModelScope.launch {
            questionUseCase.fetchQuestionsCached().collect {
                _questionList.value = it
            }
        }
    }
}