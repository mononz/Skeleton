package com.mononz.skeleton.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mononz.skeleton.R
import com.mononz.skeleton.base.BaseFragment
import com.mononz.skeleton.controller.Analytics
import com.mononz.skeleton.databinding.HomeBinding
import javax.inject.Inject

class HomeFragment @Inject constructor(
    private val analytics: Analytics,
    private val viewModelFactory: ViewModelProvider.Factory
) : BaseFragment(analytics) {

    private lateinit var binding: HomeBinding

    private val viewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (!::binding.isInitialized) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
            binding.lifecycleOwner = this
            binding.fragment = this
        }

        viewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textHome.text = it
        })

        return binding.root
    }

    override fun onResume() {
        screenName = "Home"
        super.onResume()
    }

    fun nextScreen() {
        analytics.trackNextScreen(screenName)
        val action = HomeFragmentDirections.actionHomeToSecond(screenName)
        findNavController().navigate(action)
    }
}
