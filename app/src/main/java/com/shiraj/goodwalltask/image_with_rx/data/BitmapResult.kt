package com.shiraj.goodwalltask.image_with_rx.data

import android.graphics.Bitmap

class BitmapResult private constructor(
    val state: ResponseState,
    val quality: Int = -1,
    val bitmap: Bitmap? = null
) {
    companion object {
        fun loading() = BitmapResult(ResponseState.LOADING)
        fun error() = BitmapResult(ResponseState.ERROR)
        fun success(bitmapWithQuality: BitmapWithQuality) = BitmapResult(
            ResponseState.SUCCESS,
            bitmapWithQuality.quality,
            bitmapWithQuality.bitmap
        )
    }
}