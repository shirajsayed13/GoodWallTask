package com.shiraj.goodwalltask.detail

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.shiraj.domain.model.QuestionEntity
import com.shiraj.goodwalltask.R
import com.shiraj.goodwalltask.base.BaseFragment
import com.shiraj.goodwalltask.databinding.FragmentAnswerBinding


class AnswerFragment : BaseFragment() {
    private lateinit var binding: FragmentAnswerBinding
    private val answerViewModel by viewModels<AnswerViewModel>()
    private val answerItem by lazy { arguments?.getParcelable<QuestionEntity>(ITEM) }
    private val position by lazy { arguments?.getInt(ITEM_POS) ?: 0 }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return if (::binding.isInitialized) {
            binding.root
        } else {
            binding = FragmentAnswerBinding.inflate(inflater, container, false)
            with(binding) {
                viewModel = answerViewModel
                lifecycleOwner = this@AnswerFragment
                position = this@AnswerFragment.position
                item = answerItem
                sharedElementEnterTransition =
                    TransitionInflater.from(context).inflateTransition(android.R.transition.move)
                root
            }
        }
    }

    override fun setStatusBarColor() {
        val statusColor = if (position % 2 == 1)
            R.color.colorCardBg2
        else R.color.colorCardBg1
        activity?.let {
            it.window.statusBarColor = it.getColor(statusColor)
        }
    }

    companion object {
        const val ITEM = "ANSWER"
        const val ITEM_POS = "POSITION"
    }
}