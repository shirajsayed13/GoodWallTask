package com.shiraj.goodwalltask

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.shiraj.goodwalltask.utils.Constants

@BindingAdapter("expLevel")
fun setExperienceLevel(textView: TextView, level: Int) {
    val pairs = when (level) {
        Constants.LEVEL_MEDIUM -> Pair(
            textView.context.getString(R.string.title_medium),
            AppCompatResources.getDrawable(textView.context, R.drawable.ic_car)
        )
        Constants.LEVEL_EXPERT -> Pair(
            textView.context.getString(R.string.title_expert),
            AppCompatResources.getDrawable(textView.context, R.drawable.ic_flight)
        )
        else -> Pair(
            textView.context.getString(R.string.title_beginner),
            AppCompatResources.getDrawable(textView.context, R.drawable.ic_bike)
        )
    }
    textView.text = pairs.first
    textView.setCompoundDrawablesWithIntrinsicBounds(
        null, null, pairs.second, null
    )
}

@BindingAdapter("typeIcon")
fun setTypeIcon(iv: ImageView, langCategory: Int) {
    val drawable = ContextCompat.getDrawable(
        iv.context,
        when (langCategory) {
            Constants.LANG_CAT_KOTLIN -> R.drawable.ic_kotlin
            Constants.LANG_CAT_JAVA -> R.drawable.ic_java
            else -> R.drawable.ic_android
        }
    )
    iv.setImageDrawable(drawable)
}

@BindingAdapter("show")
fun setVisibility(v: View, show: Boolean) {
    v.visibility = if (show) View.VISIBLE else View.GONE
}