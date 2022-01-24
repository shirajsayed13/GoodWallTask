package com.shiraj.goodwalltask.image_with_rx.fetcher

import com.shiraj.goodwalltask.image_with_rx.CustomImageLoadTarget
import com.shiraj.goodwalltask.image_with_rx.data.BitmapWithQuality
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import io.reactivex.SingleEmitter
import io.reactivex.SingleOnSubscribe

class ImageFetcherWithSingleSubscribe(
    private val picasso: Picasso,
    private val url: String,
    private val quality: Int
) : SingleOnSubscribe<BitmapWithQuality> {

    private val runningTargets = mutableListOf<Target>()

    override fun subscribe(emitter: SingleEmitter<BitmapWithQuality>) {
        val target = CustomImageLoadTarget(emitter, quality) {
            removeTargetAndCancelRequest(it)
        }

        runningTargets.add(target)
        picasso.load(url)
            .into(target)
    }

    private fun removeTargetAndCancelRequest(target: Target) {
        picasso.cancelRequest(target)
        runningTargets.remove(target)
    }

}