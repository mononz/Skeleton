package com.mononz.skeleton.extensions

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:src")
fun ImageView.setImageUri(url: String?) {
    this.loadImage(url)
}

@BindingAdapter("android:visibility")
fun View.visibility(condition: Boolean) {
    visibility = if (condition) View.VISIBLE else View.GONE
}
