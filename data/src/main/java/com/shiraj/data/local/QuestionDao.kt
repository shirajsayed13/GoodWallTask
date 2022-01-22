package com.shiraj.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shiraj.data.model.Question
import com.shiraj.domain.model.QuestionEntity

@Dao
interface QuestionDao {

    @Query("SELECT * FROM table_question order by id ASC")
    fun getQuestions(): List<QuestionEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(qns: List<Question>)

    @Query("DELETE FROM table_question")
    fun deleteAll()
}