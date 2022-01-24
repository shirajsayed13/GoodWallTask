package com.shiraj.goodwalltask.image_with_rx

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.shiraj.goodwalltask.R
import com.shiraj.goodwalltask.base.BaseFragment
import com.shiraj.goodwalltask.databinding.FragmentRandomImageWithRxBinding
import com.shiraj.goodwalltask.image_with_rx.data.BitmapResult
import com.shiraj.goodwalltask.image_with_rx.data.ResponseState

class RandomImageWithRxFragment : BaseFragment() {

    private lateinit var binding: FragmentRandomImageWithRxBinding
    private val imageViewModel: ImageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return if (::binding.isInitialized) {
            binding.root
        } else {
            binding = FragmentRandomImageWithRxBinding.inflate(inflater, container, false)
            with(binding) {
                headerTitle = getString(R.string.fetch_image)
                root
            }
        }
    }

    override fun subscribeUi() {
        imageViewModel.bitmapResult.observe(this, { process(it) })
        imageViewModel.loadImages(listOf(3000, 10, 300))
    }

    private fun process(result: BitmapResult?) {
        result?.let {
            when (it.state) {
                ResponseState.LOADING -> {
                    binding.loading.show()
                }
                ResponseState.ERROR -> {
                    binding.loading.show()
                }
                ResponseState.SUCCESS -> {
                    binding.loading.hide()
                    it.bitmap?.let { bitmap ->
                        showImage(bitmap)
                    }
                }
            }
        }
    }

    private fun showImage(bitmap: Bitmap) {
        binding.imageView.setImageBitmap(bitmap)
    }

    override fun onPause() {
        super.onPause()
        imageViewModel.unSubscribe()
    }
}