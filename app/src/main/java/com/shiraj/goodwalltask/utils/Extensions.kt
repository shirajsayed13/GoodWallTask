package com.shiraj.goodwalltask.utils

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.shiraj.goodwalltask.R

fun SwipeRefreshLayout.applyTheme() {
    setColorSchemeResources(
        R.color.planePrimaryTextColor,
        R.color.colorCardBg1,
        R.color.colorCardBg2
    )
    setProgressBackgroundColorSchemeResource(R.color.myBackground)
}