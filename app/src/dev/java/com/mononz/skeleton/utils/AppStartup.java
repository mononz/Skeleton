package com.mononz.skeleton.utils;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
        Stetho.initializeWithDefaults(application);
    }

    /**
     * Install interceptors
     * @return OkHttpClient.Builder
     */
    public static OkHttpClient.Builder installInterceptors(OkHttpClient.Builder builder) {

        // Create a logging interceptor for debug builds only
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // add logging interceptor to client
        builder.addInterceptor(loggingInterceptor);

        // also add stetho interceptor for extra logging with chrome inspector
        builder.addNetworkInterceptor(new StethoInterceptor());

        return builder;
    }
}