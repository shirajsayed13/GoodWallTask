package com.shiraj.goodwalltask.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.shiraj.domain.model.Output
import com.shiraj.domain.model.QuestionEntity
import com.shiraj.goodwalltask.R
import com.shiraj.goodwalltask.base.BaseFragment
import com.shiraj.goodwalltask.databinding.FragmentQuestionsBinding
import com.shiraj.goodwalltask.detail.AnswerFragment
import com.shiraj.goodwalltask.utils.applyTheme

class QuestionsFragment : BaseFragment() {

    private val questionsViewModel: QuestionsViewModel by viewModels()
    private lateinit var binding: FragmentQuestionsBinding
    private lateinit var questionsAdapter: QuestionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return if (::binding.isInitialized) {
            binding.root
        } else {
            binding = FragmentQuestionsBinding.inflate(inflater, container, false)
            with(binding) {
                headerTitle = getString(R.string.questions)
                root
            }
        }
    }


    private val onQuestionClick: (details: QuestionEntity, view: View, position: Int) -> Unit =
        { details, view, position ->
            val extras = FragmentNavigatorExtras(
                view to details.question
            )
            findNavController().navigate(
                R.id.action_questionsFragment_to_answerFragment,
                bundleOf(AnswerFragment.ITEM to details, AnswerFragment.ITEM_POS to position),
                null,
                extras
            )
        }

    override fun subscribeUi() {
        onTriggerQuestions()
        binding.swipeRefresh.applyTheme()
        questionsAdapter = QuestionsAdapter(arrayListOf(), onQuestionClick)
        binding.rvQuestions.adapter = questionsAdapter
        binding.swipeRefresh.setOnRefreshListener {
            onTriggerQuestions()
        }
        questionsViewModel.questionList.observe(viewLifecycleOwner) { result ->

            when (result.status) {
                Output.Status.SUCCESS -> {
                    result.data?.let { list ->
                        questionsAdapter.update(list)
                    }
                    binding.swipeRefresh.isRefreshing = false
                }

                Output.Status.ERROR -> {
                    result.message?.let {
                        showError(it) {
                            onTriggerQuestions()
                        }
                    }
                    binding.swipeRefresh.isRefreshing = false
                }

                Output.Status.LOADING -> {
                    binding.swipeRefresh.isRefreshing = true
                }
            }
        }
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnFav.setOnClickListener {
            findNavController().navigate(R.id.action_questionsFragment_to_randomQuotesFragment)
        }
    }

    private fun onTriggerQuestions() {
        questionsViewModel.fetchQuestionsCached()
    }
}