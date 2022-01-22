package com.shiraj.goodwalltask.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shiraj.domain.model.Output
import com.shiraj.domain.usecase.QuestionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val questionUseCase: QuestionUseCase
) : ViewModel() {
    private val _questionOk = MutableLiveData<Output<Boolean>>()
    val localQuestionOk = MutableLiveData<Boolean>()
    val questionOk = _questionOk

    init {
        fetchQuestions()
    }

    fun fetchQuestions() {
        viewModelScope.launch {
            questionUseCase.fetchQuestions().collect {
                _questionOk.value = it
            }
        }
    }

    fun fetchCachedQuestions() {
        viewModelScope.launch {
            questionUseCase.fetchQuestionsCached().collect { result ->
                if (!result.data.isNullOrEmpty()) {
                    localQuestionOk.value = true
                }
            }
        }
    }
}