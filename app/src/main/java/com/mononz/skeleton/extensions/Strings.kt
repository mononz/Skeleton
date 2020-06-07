package com.mononz.skeleton.extensions

fun String?.isSVG(): Boolean {
    return this?.endsWith(".svg") == true
}
