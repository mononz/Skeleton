package com.mononz.skeleton.utils;

import android.app.Application;

import okhttp3.OkHttpClient;
import timber.log.Timber;

public class AppStartup {

    public static void start(Application application) {
        Timber.plant(new Timber.DebugTree());
        // Do nothing. We don't want stetho on release builds
    }

    public static OkHttpClient.Builder installInterceptors(OkHttpClient.Builder builder) {
        // Do nothing. We don't want install any network inspectors on release builds
        return builder;
    }
}