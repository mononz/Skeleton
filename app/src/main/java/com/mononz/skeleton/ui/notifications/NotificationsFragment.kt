package com.mononz.skeleton.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mononz.skeleton.R
import com.mononz.skeleton.base.BaseFragment
import com.mononz.skeleton.controller.Analytics.Companion.SCREEN_NOTIFICATIONS
import com.mononz.skeleton.databinding.NotificationsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationsFragment : BaseFragment() {

    private lateinit var binding: NotificationsBinding

    private val viewModel: NotificationsViewModel by viewModels()

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
        super.onResume()
        screenName = SCREEN_NOTIFICATIONS

        analytics.trackScreen(activity, screenName)
    }

    fun nextScreen() {
        analytics.trackNextScreen(screenName)
        val action = NotificationsFragmentDirections.actionNotificationsToSecond(screenName)
        navigate(action, true)
    }
}
