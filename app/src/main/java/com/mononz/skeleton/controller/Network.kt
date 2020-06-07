@file:Suppress("DeferredIsResult")

package com.mononz.skeleton.controller

import com.mononz.skeleton.data.request.FakeRequest
import com.mononz.skeleton.data.response.SkeletonDataResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface Network {

    @GET("skeletons")
    fun getSkeletons(
        @Header("Authorization") authorization: String
    ): Deferred<SkeletonDataResponse>

    @POST("skeleton")
    fun createSkeleton(
        @Body body: FakeRequest
    ): Deferred<Unit>
}
