package com.mononz.skeleton.injection

import android.app.Application
import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mononz.skeleton.BuildConfig.API_PATH
import com.mononz.skeleton.Constants.PREFERENCE_NAME
import com.mononz.skeleton.controller.Analytics
import com.mononz.skeleton.controller.Device
import com.mononz.skeleton.controller.Network
import com.mononz.skeleton.controller.Repository
import com.mononz.skeleton.controller.Session
import com.mononz.skeleton.data.NetworkInterceptor
import com.mononz.skeleton.utils.AppStartup
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    internal fun providesOkHttpClient(device: Device): OkHttpClient {
        val interceptor = NetworkInterceptor(device)
        val builder = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
        return AppStartup.installInterceptors(builder).build()
    }

    @Provides
    @Singleton
    internal fun providesRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_PATH)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    internal fun providesDevice(context: Application): Device {
        return Device(context)
    }

    @Provides
    @Singleton
    internal fun providesRepository(network: Network): Repository {
        return Repository(network)
    }

    @Provides
    @Singleton
    internal fun providesNetwork(retrofit: Retrofit): Network {
        return retrofit.create(Network::class.java)
    }

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
