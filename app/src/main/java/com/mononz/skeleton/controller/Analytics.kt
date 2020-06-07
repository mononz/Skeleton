package com.mononz.skeleton.controller

import android.app.Activity
import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.mononz.skeleton.data.response.SkeletonResponse
import javax.inject.Inject
import javax.inject.Singleton
import timber.log.Timber

@Singleton
class Analytics @Inject
constructor(context: Application) {

    init {
        Timber.d("Initialising firebase")
        FirebaseApp.initializeApp(context)
    }

    fun trackScreen(activity: Activity?, screenName: String) {
        Firebase.analytics.setCurrentScreen(activity ?: return, screenName, null)
    }

    fun trackError(message: String, screen: String) {
        Firebase.analytics.logEvent("error") {
            param("message", message)
            param("screen", screen)
        }
    }

    fun clickSkeleton(skeleton: SkeletonResponse) {
        Firebase.analytics.logEvent("next_screen") {
            param("id", skeleton.id ?: -1)
            param("name", skeleton.name ?: "")
        }
    }

    fun trackNextScreen(from: String) {
        Firebase.analytics.logEvent("next_screen") {
            param("from", from)
        }
    }

    fun setUserProperty(property: String, value: String) {
        Firebase.analytics.setUserProperty(property, value)
    }

    companion object {
        const val SCREEN_HOME = "Home"
        const val SCREEN_DASHBOARD = "Dashboard"
        const val SCREEN_NOTIFICATIONS = "Notifications"
        const val SCREEN_SECOND = "Second"
        const val SCREEN_DETAIL = "Detail"
    }
}
