@file:Suppress("DeferredIsResult")

package com.mononz.skeleton.controller

import androidx.annotation.UiThread
import androidx.lifecycle.MutableLiveData
import com.mononz.skeleton.data.Resource
import com.mononz.skeleton.data.response.SkeletonDataResponse
import com.mononz.skeleton.data.response.SkeletonResponse
import com.mononz.skeleton.extensions.asNetworkError
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Singleton
class Repository(private val network: Network) {

    val skeletons = MutableLiveData<Resource<SkeletonDataResponse>>()

    @UiThread
    fun updateSkeleton(showLoading: Boolean) {
        if (showLoading) {
            skeletons.value = Resource.loading(skeletons.value?.data)
        }

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = network.getSkeletons("some_auth").await()
                skeletons.value = Resource.success(response)
            } catch (e: Exception) {
                skeletons.value = Resource.error(e.asNetworkError())
            }
        }
    }

    fun getSkeleton(id: Long): SkeletonResponse? {
        return skeletons.value?.data?.skeletons?.firstOrNull { it.id == id }
    }
}
