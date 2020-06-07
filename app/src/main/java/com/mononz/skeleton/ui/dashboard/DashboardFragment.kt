package com.mononz.skeleton.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mononz.skeleton.R
import com.mononz.skeleton.controller.Analytics
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class DashboardFragment @Inject constructor(
    private val analytics: Analytics,
    private val viewModelFactory: ViewModelProvider.Factory
) : Fragment() {

    private val viewModel: DashboardViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)

        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onResume() {
        super.onResume()
        analytics.trackScreen(activity, "Home")
    }
}