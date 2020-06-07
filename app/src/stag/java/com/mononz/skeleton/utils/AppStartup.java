package com.mononz.skeleton.utils;

import android.app.Application;

import okhttp3.OkHttpClient;
import timber.log.Timber;

/**
 * Stetho utils class
 */
public class AppStartup {

    /**
     * Application context
     * @param application Application
     */
    public static void start(Application application) {
        Timber.plant(new Timber.DebugTree());
        // Do nothing. We don't want stetho on release builds
    }

    /**
     * Install interceptors
     * @return OkHttpClient.Builder
     */
    public static OkHttpClient.Builder installInterceptors(OkHttpClient.Builder builder) {
        // Do nothing. We don't want install any network inspectors on release builds
        return builder;
    }
}