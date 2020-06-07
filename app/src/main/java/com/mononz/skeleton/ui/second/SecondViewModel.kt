package com.mononz.skeleton.ui.second

import androidx.annotation.UiThread
import androidx.lifecycle.ViewModel
import com.mononz.skeleton.controller.Network
import com.mononz.skeleton.data.Resource
import com.mononz.skeleton.data.SingleLiveEvent
import com.mononz.skeleton.data.request.FakeRequest
import com.mononz.skeleton.extensions.asNetworkError
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SecondViewModel @Inject constructor(
    private val network: Network
) : ViewModel() {

    @UiThread
    fun createSkeleton(anything: String): SingleLiveEvent<Resource<Unit>> {
        val event = SingleLiveEvent<Resource<Unit>>()
        event.value = Resource.loading()

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val body = FakeRequest(anything)
                val response = network.createSkeleton(body).await()

                event.value = Resource.success(response)
            } catch (e: Exception) {
                event.value = Resource.error(e.asNetworkError())
            }
        }

        return event
    }
}
