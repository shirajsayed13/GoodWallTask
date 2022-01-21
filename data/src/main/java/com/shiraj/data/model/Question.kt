package com.shiraj.data.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question(
    @NonNull
    @PrimaryKey
    val id: Int,
    val level: Int,
    val langCategory: Int,
    val hashtags: List<String>,
    val question: String,
    val answer: String
) : Parcelable {

}
