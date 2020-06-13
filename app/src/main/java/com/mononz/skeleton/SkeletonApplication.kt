package com.mononz.skeleton

import android.app.Application
import com.mononz.skeleton.utils.AppStartup
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SkeletonApplication : Application() {

//    @Inject lateinit var analytics: Analytics
//    @Inject lateinit var session: Session

    override fun onCreate() {
        super.onCreate()

        AppStartup.start(this)

//        val openCount = session.incrementAppOpenCounter()
//        analytics.setUserProperty("open_count", openCount.toString())
    }
}
