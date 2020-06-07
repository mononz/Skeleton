@file:Suppress("DeferredIsResult")

package com.mononz.skeleton.controller

import com.mononz.skeleton.data.response.SkeletonDataResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Header

interface Network {

    @GET("skeletons")
    fun getSkeletons(
        @Header("Authorization") authorization: String
    ): Deferred<SkeletonDataResponse>
}
