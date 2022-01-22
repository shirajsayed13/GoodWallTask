package com.shiraj.goodwalltask.random_quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shiraj.goodwalltask.R
import com.shiraj.goodwalltask.base.BaseFragment

//TODO implement this feature using RxJava just for demo
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
}