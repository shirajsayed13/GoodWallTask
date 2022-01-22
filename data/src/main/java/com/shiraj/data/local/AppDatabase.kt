package com.shiraj.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shiraj.data.model.Question

@Database(
    entities = [Question::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(GenreConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
}