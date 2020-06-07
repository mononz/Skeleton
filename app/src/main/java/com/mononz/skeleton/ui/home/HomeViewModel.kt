package com.mononz.skeleton.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mononz.skeleton.controller.Repository
import com.mononz.skeleton.data.Resource
import com.mononz.skeleton.data.response.SkeletonDataResponse
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun observeSkeletons(): MutableLiveData<Resource<SkeletonDataResponse>> {
        return repository.skeletons
    }

    fun updateSkeleton(showLoading: Boolean) {
        repository.updateSkeleton(showLoading)
    }
}
