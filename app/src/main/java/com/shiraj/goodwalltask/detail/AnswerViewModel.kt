package com.shiraj.goodwalltask.detail

import androidx.lifecycle.ViewModel
import com.shiraj.domain.usecase.QuestionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnswerViewModel @Inject constructor(
    private val questionUseCase: QuestionUseCase
) : ViewModel() {
}