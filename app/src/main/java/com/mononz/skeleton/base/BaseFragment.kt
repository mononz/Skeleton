package com.mononz.skeleton.base

import androidx.fragment.app.Fragment
import com.mononz.skeleton.controller.Analytics

abstract class BaseFragment constructor(
    private val analytics: Analytics
) : Fragment() {

    var screenName = ""

    override fun onResume() {
        super.onResume()

        if (screenName.isNotEmpty())
            analytics.trackScreen(activity, screenName)
    }
}
