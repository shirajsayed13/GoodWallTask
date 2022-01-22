package com.shiraj.data.local

import androidx.room.TypeConverter

/**
 * converts List to and from String
 */
class GenreConverters {

    @TypeConverter
    fun listToString(values: List<String>): String {
        return values.joinToString(",")
    }

    @TypeConverter
    fun stringToList(value: String): List<String> {
        val intList = mutableListOf<String>()
        value.split(",").forEach {
            intList.add(it)
        }
        return intList
    }

}