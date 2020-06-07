package com.mononz.skeleton

import com.mononz.skeleton.controller.Analytics
import com.mononz.skeleton.controller.Session
import com.mononz.skeleton.injection.component.DaggerAppComponent
import com.mononz.skeleton.utils.AppStartup
import dagger.android.DaggerApplication
import javax.inject.Inject

class SkeletonApplication : DaggerApplication() {

    @Inject lateinit var analytics: Analytics
    @Inject lateinit var session: Session

    override fun onCreate() {
        super.onCreate()

        AppStartup.start(this)

        val openCount = session.incrementAppOpenCounter()
        analytics.setUserProperty("open_count", openCount.toString())
    }

    private val applicationInjector = DaggerAppComponent.builder()
            .application(this)
            .build()

    override fun applicationInjector() = applicationInjector
}
