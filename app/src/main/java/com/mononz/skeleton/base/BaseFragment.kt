package com.mononz.skeleton.base

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.mononz.skeleton.R
import com.mononz.skeleton.controller.Analytics
import com.mononz.skeleton.data.ErrorObject

abstract class BaseFragment constructor(
    private val analytics: Analytics
) : Fragment() {

    var screenName = ""

    open fun toast(error: ErrorObject?) {
        error?.let {
            Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
            analytics.trackError(it.message, screenName)
        }
    }

    open fun openWebsite(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        activity?.startActivity(intent)
    }

    open fun navigate(action: NavDirections, fade: Boolean = false) {
        val navigationAnim = if (fade) {
            NavOptions.Builder()
                .setEnterAnim(R.anim.fade_in)
                .setExitAnim(R.anim.fade_out)
                .setPopEnterAnim(R.anim.fade_in)
                .setPopExitAnim(R.anim.fade_out)
                .build()
        } else {
            NavOptions.Builder()
                .setEnterAnim(R.anim.slide_in_right)
                .setExitAnim(R.anim.slide_out_left)
                .setPopEnterAnim(R.anim.slide_in_left)
                .setPopExitAnim(R.anim.slide_out_right)
                .build()
        }
        findNavController().navigate(action, navigationAnim)
    }
}
