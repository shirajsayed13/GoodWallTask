package com.shiraj.domain.model

import android.os.Parcelable
import androidx.annotation.NonNull
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuestionEntity(
    @NonNull
    val id: Int,
    val level: Int,
    val langCategory: Int,
    val hashtags: List<String>,
    val question: String,
    val answer: String
) : Parcelable {
    fun generateHashTags() = hashtags.joinToString(prefix = "#", separator = " #")
}