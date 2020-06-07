package com.mononz.skeleton.controller

import android.app.Activity
import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
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

    fun trackNextScreen(from: String) {
        Firebase.analytics.logEvent("next_screen") {
            param("from", from)
        }
    }

    fun setUserProperty(property: String, value: String) {
        Firebase.analytics.setUserProperty(property, value)
    }
}
