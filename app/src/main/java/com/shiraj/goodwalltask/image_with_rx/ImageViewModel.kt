package com.shiraj.goodwalltask.image_with_rx

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shiraj.goodwalltask.BuildConfig
import com.shiraj.goodwalltask.image_with_rx.data.BitmapResult
import com.shiraj.goodwalltask.image_with_rx.data.BitmapWithQuality
import com.shiraj.goodwalltask.image_with_rx.fetcher.ImageFetcher
import com.squareup.picasso.Picasso
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor() : ViewModel() {

    private val disposable = CompositeDisposable()
    private val fetcher = ImageFetcher(Picasso.get())

    val bitmapResult = MutableLiveData<BitmapResult>()

    fun loadImages(qualities: List<Int>) {
        bitmapResult.value = BitmapResult.loading()
        disposable.add(fetcher.loadProgressively(BuildConfig.BASE_IMAGE_URL, qualities)
            .filter { getCurrentQuality() < it.quality }
            .subscribeBy(
                onNext = { applyImage(it) },
                onComplete = { postErrorIfNotSufficientQuality() }
            )
        )
    }


    private fun getCurrentQuality(): Int {
        return bitmapResult.value?.quality ?: -1
    }

    private fun applyImage(bitmap: BitmapWithQuality) {
        bitmapResult.value = BitmapResult.success(bitmap)
    }

    private fun postErrorIfNotSufficientQuality() {
        if (getCurrentQuality() < 0) {
            bitmapResult.value = BitmapResult.error()
        }
    }

    fun unSubscribe() {
        disposable.dispose()
    }

}