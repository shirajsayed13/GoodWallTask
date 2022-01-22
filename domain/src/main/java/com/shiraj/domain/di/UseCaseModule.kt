package com.shiraj.domain.di

import com.shiraj.domain.usecase.QuestionUseCase
import com.shiraj.domain.usecase.QuestionUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindQuestionUseCase(useCaseImpl: QuestionUseCaseImpl): QuestionUseCase
}