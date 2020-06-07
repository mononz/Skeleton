package com.mononz.skeleton.ui.detail

import androidx.lifecycle.ViewModel
import com.mononz.skeleton.controller.Repository
import com.mononz.skeleton.data.response.SkeletonResponse
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun getSkeleton(id: Long): SkeletonResponse? {
        return repository.getSkeleton(id)
    }

    fun getSkeletonSource(): String? {
        return repository.skeletons.value?.data?.source
    }
}
