package com.shiraj.goodwalltask.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.shiraj.goodwalltask.base.BaseFragment
import com.shiraj.goodwalltask.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment() {

    private lateinit var binding: FragmentSplashBinding
    private val splashViewModel by viewModels<SplashViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (::binding.isInitialized) {
            binding.root
        } else {
            binding = FragmentSplashBinding.inflate(inflater, container, false)
            with(binding) {
                viewModel = splashViewModel
                lifecycleOwner = this@SplashFragment
                root
            }
        }
    }
}