package com.shiraj.goodwalltask.image_with_rx.fetcher

import com.shiraj.goodwalltask.image_with_rx.data.BitmapWithQuality
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.Single

class ImageFetcher(private val picasso: Picasso) {

    fun loadProgressively(baseUrl: String, qualities: List<Int>): Observable<BitmapWithQuality> {
        return qualities
            .map { quality -> Pair(createUrl(baseUrl, quality), quality) }
            .map { loadImageAndIgnoreError(it) }
            .reduce { acc, observable -> Observable.merge(acc, observable) }
    }

    private fun loadImageAndIgnoreError(urlWithQuality: Pair<String, Int>): Observable<BitmapWithQuality> {
        val (url, quality) = urlWithQuality
        return loadImageAndIgnoreError(url, quality)
    }

    private fun loadImageAndIgnoreError(url: String, quality: Int): Observable<BitmapWithQuality> {
        return Single
            .create(ImageFetcherWithSingleSubscribe(picasso, url, quality))
            .toObservable()
            .onErrorResumeNext(Observable.empty())
    }

    private fun createUrl(url: String, size: Int): String =
        "$url/$size/$size?image=0"
}