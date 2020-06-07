package com.mononz.skeleton.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mononz.skeleton.R
import com.mononz.skeleton.controller.Analytics
import com.mononz.skeleton.databinding.DashboardBinding
import javax.inject.Inject

class DashboardFragment @Inject constructor(
    private val analytics: Analytics,
    private val viewModelFactory: ViewModelProvider.Factory
) : Fragment() {

    private lateinit var binding: DashboardBinding

    private val viewModel: DashboardViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (!::binding.isInitialized) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)
            binding.lifecycleOwner = this
            binding.fragment = this
        }

        viewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textDashboard.text = it
        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        analytics.trackScreen(activity, "Dashboard")
    }

    fun nextScreen() {
        val action = DashboardFragmentDirections.actionDashToSecond("Dashboard")
        findNavController().navigate(action)
    }
}