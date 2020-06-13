package com.mononz.skeleton.ui.dashboard

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mononz.skeleton.controller.Session
import javax.inject.Inject

class DashboardViewModel @ViewModelInject constructor(
    private val session: Session
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "App has been opened ${session.getAppOpenCounter()} times"
    }
    val text: LiveData<String> = _text
}
