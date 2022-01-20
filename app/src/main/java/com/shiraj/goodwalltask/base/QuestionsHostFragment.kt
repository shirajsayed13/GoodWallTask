package com.shiraj.goodwalltask.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shiraj.goodwalltask.R
import com.shiraj.goodwalltask.databinding.FragmentQuestionsHostBinding

class QuestionsHostFragment : BaseFragment() {
    private lateinit var binding: FragmentQuestionsHostBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (::binding.isInitialized) {
            binding.root
        } else {
            binding = FragmentQuestionsHostBinding.inflate(inflater, container, false)
            with(binding) {
                val navView : BottomNavigationView = navView
                val navHostFragment =
                    childFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
                val navController = navHostFragment.navController
                navView.setupWithNavController(navController)
                root
            }
        }
    }
}