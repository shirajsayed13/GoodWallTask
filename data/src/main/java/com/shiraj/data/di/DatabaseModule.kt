package com.shiraj.data.di

import android.content.Context
import androidx.room.Room
import com.shiraj.data.BuildConfig
import com.shiraj.data.local.AppDatabase
import com.shiraj.data.local.QuestionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            BuildConfig.DB_NAME
        ).build()
    }

    @Provides
    fun provideQuestionDao(appDatabase: AppDatabase): QuestionDao {
        return appDatabase.questionDao()
    }
}