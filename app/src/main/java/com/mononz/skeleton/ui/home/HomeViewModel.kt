package com.mononz.skeleton.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mononz.skeleton.controller.Session
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val session: Session
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}