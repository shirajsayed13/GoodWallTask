package com.shiraj.goodwalltask.random_quotes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shiraj.goodwalltask.R
import com.shiraj.goodwalltask.base.BaseFragment

class RandomQuotesFragment : BaseFragment() {

    companion object {
        fun newInstance() = RandomQuotesFragment()
    }

    private lateinit var viewModel: RandomQuotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_random_quotes, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RandomQuotesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}