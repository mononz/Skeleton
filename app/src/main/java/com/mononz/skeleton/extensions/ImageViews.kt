package com.mononz.skeleton.extensions

import android.graphics.drawable.PictureDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mononz.skeleton.utils.svg.SvgSoftwareLayerSetter

fun ImageView.loadImage(url: String?, loading: Int? = null, error: Int? = null) {

    var options = RequestOptions()
            .error(null)

    // Add a loading/placeholder image if provided
    if (loading != null) {
        options = options.placeholder(loading)
    }

    // Add a error image if provided
    if (error != null) {
        options = options.error(error)
    }

    // Load image differently if name indicates an SVG
    if (url?.isSVG() == true) {
        Glide.with(context)
                .`as`(PictureDrawable::class.java)
                .apply(options)
                .listener(SvgSoftwareLayerSetter())
                .load(url)
                .into(this)
    } else {
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(this)
    }
}
