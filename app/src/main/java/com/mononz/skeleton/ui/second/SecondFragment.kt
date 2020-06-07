package com.mononz.skeleton.ui.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mononz.skeleton.R
import com.mononz.skeleton.base.BaseFragment
import com.mononz.skeleton.controller.Analytics
import com.mononz.skeleton.controller.Analytics.Companion.SCREEN_SECOND
import com.mononz.skeleton.databinding.SecondBinding
import javax.inject.Inject

class SecondFragment @Inject constructor(
    private val analytics: Analytics,
    private val viewModelFactory: ViewModelProvider.Factory
) : BaseFragment(analytics) {

    private lateinit var binding: SecondBinding

    private val viewModel: SecondViewModel by viewModels {
        viewModelFactory
    }

    private val args: SecondFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (!::binding.isInitialized) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false)
            binding.lifecycleOwner = this
        }

        binding.textSecond.text = "Came from: ${args.from}"

        binding.toolbar.apply {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        screenName = SCREEN_SECOND

        analytics.trackScreen(activity, screenName)
    }
}
