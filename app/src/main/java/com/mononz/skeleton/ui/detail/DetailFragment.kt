package com.mononz.skeleton.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mononz.skeleton.R
import com.mononz.skeleton.base.BaseFragment
import com.mononz.skeleton.controller.Analytics.Companion.SCREEN_DETAIL
import com.mononz.skeleton.data.response.SkeletonResponse
import com.mononz.skeleton.databinding.DetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment() {

    private lateinit var binding: DetailBinding

    private val viewModel: DetailViewModel by viewModels()

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (!::binding.isInitialized) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
            binding.lifecycleOwner = this
            binding.fragment = this
        }

        binding.obj = viewModel.getSkeleton(args.id) ?: SkeletonResponse(-1, "Skeleton Not Found", null, null)
        binding.source = viewModel.getSkeletonSource()

        binding.toolbar.apply {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        screenName = SCREEN_DETAIL

        analytics.trackScreen(activity, screenName)
    }

    fun goToSource(source: String) {
        openWebsite(source)
    }
}
