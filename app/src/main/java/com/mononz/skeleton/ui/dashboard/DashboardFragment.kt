package com.mononz.skeleton.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mononz.skeleton.R
import com.mononz.skeleton.base.BaseFragment
import com.mononz.skeleton.controller.Analytics.Companion.SCREEN_DASHBOARD
import com.mononz.skeleton.databinding.DashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment() {

    private lateinit var binding: DashboardBinding

    private val viewModel: DashboardViewModel by viewModels()

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
        screenName = SCREEN_DASHBOARD

        analytics.trackScreen(activity, screenName)
    }

    fun nextScreen() {
        analytics.trackNextScreen(screenName)
        val action = DashboardFragmentDirections.actionDashToSecond(screenName)
        navigate(action, true)
    }
}
