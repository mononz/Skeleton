package com.mononz.skeleton.injection.module

import android.app.Application
import android.content.Context
import com.mononz.skeleton.Constants.PREFERENCE_NAME
import com.mononz.skeleton.controller.Analytics
import com.mononz.skeleton.controller.Session
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ContributesViewModel::class])
class AppModule {

    @Provides
    @Singleton
    internal fun providesSession(application: Application): Session {
        return Session(application.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE))
    }

    @Provides
    @Singleton
    internal fun providesFirebase(application: Application): Analytics {
        return Analytics(application)
    }
}
