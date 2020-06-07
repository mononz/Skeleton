package com.mononz.skeleton.controller

import android.app.Activity
import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Analytics @Inject
constructor(context: Application) {

    private val analytics = Firebase.analytics

    init {
        Timber.d("Initialising firebase")
        FirebaseApp.initializeApp(context)
    }

    fun sayHello(): String {
        return "Hello"
    }

    fun trackScreen(activity: Activity?, screenName: String) {
        analytics.setCurrentScreen(activity ?: return, screenName, null)
    }

    fun trackLogin(email: String) {
        analytics.logEvent("login") {
            param("email", email)
        }
    }

    fun setUserProperty(property: String, value: String) {
        analytics.setUserProperty(property, value)
    }
}
