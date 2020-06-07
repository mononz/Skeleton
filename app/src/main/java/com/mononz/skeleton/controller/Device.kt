package com.mononz.skeleton.controller

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build

class Device(private val context: Context) {

    private var mConnectivityManager: ConnectivityManager? = null

    val deviceModel: String
        get() = Build.MODEL

    val deviceVersion: String
        get() = Build.VERSION.RELEASE

    val isNetworkOnline: Boolean
        get() {
            if (mConnectivityManager == null) {
                mConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            }
            var online = true
            if (mConnectivityManager != null) {
                val activeNetwork = mConnectivityManager!!.activeNetworkInfo
                online = activeNetwork != null && activeNetwork.isConnected
            }
            return online
        }
}
