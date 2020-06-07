package com.mononz.skeleton.data

import com.mononz.skeleton.BuildConfig.API_PATH
import com.mononz.skeleton.BuildConfig.VERSION_CODE
import com.mononz.skeleton.BuildConfig.VERSION_NAME
import com.mononz.skeleton.controller.Device
import java.io.IOException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor(private val device: Device) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        if (!device.isNetworkOnline) {
            throw NoConnectivityException()
        }

        val originalRequest = chain.request()
        val builder = originalRequest.newBuilder()

        // Only sending headers to our servers (not google maps etc);
        if (originalRequest.url.toString().startsWith(API_PATH)) {
            builder.addHeader("X-DEVICE-TYPE", "Android")
            builder.addHeader("X-DEVICE-MODEL", device.deviceModel)
            builder.addHeader("X-DEVICE-VERSION", device.deviceVersion)
            builder.addHeader("X-APP-VERSION", VERSION_NAME)
            builder.addHeader("X-APP-VERSION-CODE", VERSION_CODE.toString())
        }

        return chain.proceed(builder.build())
    }
}
