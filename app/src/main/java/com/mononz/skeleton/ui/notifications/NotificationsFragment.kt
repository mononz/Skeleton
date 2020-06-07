package com.mononz.skeleton.ui.notifications

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
import com.mononz.skeleton.databinding.NotificationsBinding
import javax.inject.Inject

class NotificationsFragment @Inject constructor(
    private val analytics: Analytics,
    private val viewModelFactory: ViewModelProvider.Factory
) : BaseFragment(analytics) {

    private lateinit var binding: NotificationsBinding

    private val viewModel: NotificationsViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (!::binding.isInitialized) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notifications, container, false)
            binding.lifecycleOwner = this
            binding.fragment = this
        }

        viewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textNotifications.text = it
        })

        return binding.root
    }

    override fun onResume() {
        screenName = "Notifications"
        super.onResume()
    }

    fun nextScreen() {
        analytics.trackNextScreen(screenName)
        val action = NotificationsFragmentDirections.actionNotificationsToSecond(screenName)
        findNavController().navigate(action)
    }
}
