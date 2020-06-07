package com.mononz.skeleton.data

import com.google.gson.Gson
import com.mononz.skeleton.data.response.ErrorResponse
import com.mononz.skeleton.extensions.NetworkErrors

class ErrorObject {

    var code: String
    var message: String

    constructor(message: String?) {
        this.code = "0"
        try {
            this.message = Gson().toJson(message, ErrorResponse::class.java)
        } catch (e: Exception) {
            this.message = message ?: "Unknown"
        }
    }

    constructor(code: String?, message: String?) {
        this.code = code ?: "0"
        this.message = message ?: "Unknown"
    }

    constructor(err: NetworkErrors) {
        this.code = "0"
        this.message = err.title
    }

    override fun toString(): String {
        return "ErrorObject{code='$code', message='$message'}"
    }
}
