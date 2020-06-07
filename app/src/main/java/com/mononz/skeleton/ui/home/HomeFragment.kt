package com.mononz.skeleton.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mononz.skeleton.R
import com.mononz.skeleton.base.BaseFragment
import com.mononz.skeleton.controller.Analytics
import com.mononz.skeleton.controller.Analytics.Companion.SCREEN_HOME
import com.mononz.skeleton.data.Status.ERROR
import com.mononz.skeleton.data.Status.LOADING
import com.mononz.skeleton.data.Status.SUCCESS
import com.mononz.skeleton.data.response.SkeletonResponse
import com.mononz.skeleton.databinding.HomeBinding
import javax.inject.Inject
import timber.log.Timber

class HomeFragment @Inject constructor(
    private val analytics: Analytics,
    private val viewModelFactory: ViewModelProvider.Factory
) : BaseFragment(analytics), SwipeRefreshLayout.OnRefreshListener, HomeAdapter.Callback {

    private lateinit var binding: HomeBinding

    private val mAdapter = HomeAdapter()

    private val viewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (!::binding.isInitialized) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
            binding.lifecycleOwner = this
            binding.fragment = this
        }

        binding.skeletons.apply {
            adapter = mAdapter.apply {
                this.setCallback(this@HomeFragment)
            }
            setHasFixedSize(true)
        }

        binding.swipeRefreshLayout.setOnRefreshListener(this)

        viewModel.observeSkeletons().observe(viewLifecycleOwner, Observer {
            Timber.d(it.status.name)
            binding.swipeRefreshLayout.isRefreshing = it.status == LOADING
            when (it.status) {
                LOADING -> { }
                SUCCESS -> {
                    val data = it.data ?: return@Observer
                    mAdapter.setData(data.skeletons ?: emptyList())
                    binding.source = data.source ?: ""
                }
                ERROR -> {
                    toast(it.error)
                }
            }
        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        screenName = SCREEN_HOME

        analytics.trackScreen(activity, screenName)

        if (mAdapter.itemCount == 0) {
            viewModel.updateSkeleton(true)
        }
    }

    override fun onRefresh() {
        Timber.d("onRefresh")
        viewModel.updateSkeleton(false)
    }

    override fun itemSelected(skeleton: SkeletonResponse, position: Int) {
        analytics.clickSkeleton(skeleton)
        skeleton.id?.let {
            val action = HomeFragmentDirections.actionHomeToDetail(it)
            navigate(action)
        }
    }

    fun goToSource(source: String) {
        openWebsite(source)
    }
}
