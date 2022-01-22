package com.shiraj.goodwalltask.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.shiraj.domain.model.Output
import com.shiraj.goodwalltask.R
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

    override fun subscribeUi() {
        splashViewModel.localQuestionOk.observe(viewLifecycleOwner, { isOk ->
            if (isOk) {
                hideError()
                navigateToQuestions()
            }
        })

        splashViewModel.questionOk.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Output.Status.SUCCESS -> {
                    binding.loading.hide()
                    navigateToQuestions()
                }
                Output.Status.ERROR -> {
                    result.message?.let {
                        splashViewModel.fetchCachedQuestions()
                        showError(it) {
                            splashViewModel.fetchQuestions()
                        }
                    }
                    binding.loading.hide()
                }

                Output.Status.LOADING -> {
                    binding.loading.show()
                }
            }
        }
    }

    private fun navigateToQuestions() {
        val extras = FragmentNavigatorExtras(
            binding.profPic to getString(R.string.app_name)
        )
        findNavController().navigate(
            R.id.action_splashFragment_to_questionsFragment,
            null,
            null,
            extras
        )
    }
}