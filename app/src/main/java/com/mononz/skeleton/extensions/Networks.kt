package com.mononz.skeleton.extensions

import com.google.gson.Gson
import com.mononz.skeleton.data.ErrorObject
import com.mononz.skeleton.data.NoConnectivityException
import com.mononz.skeleton.data.response.ErrorResponse
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import retrofit2.HttpException
import timber.log.Timber

enum class NetworkErrors(var title: String) {

    NO_NETWORK("No network access"),
    UNKNOWN("Something went wrong :("),
    NETWORK_CHANGE("Network changed"),
    UNAVAILABLE("Server Unavailable"),
    TIMEOUT("Timeout"),
    SERVER_ERROR("Server Error");

    override fun toString(): String {
        return title
    }
}

fun Throwable?.asNetworkError(): ErrorObject? {

    val err: ErrorObject = when {
        this is HttpException ->
            this.getErrorResponse()
        this is NoConnectivityException ->
            ErrorObject(NetworkErrors.NO_NETWORK)
        this?.message == null ->
            ErrorObject(NetworkErrors.UNKNOWN)
        this.message == "Software caused connection abort" ->
            ErrorObject(NetworkErrors.NETWORK_CHANGE)
        this is ConnectException ->
            ErrorObject(NetworkErrors.UNAVAILABLE)
        this is SocketTimeoutException ->
            ErrorObject(NetworkErrors.TIMEOUT)
        this is SocketException ->
            ErrorObject(NetworkErrors.UNAVAILABLE)
        this is UnknownHostException ->
            ErrorObject(NetworkErrors.NETWORK_CHANGE)
        this.message?.contains("Use JsonReader.setLenient") == true ->
            ErrorObject(NetworkErrors.UNKNOWN)
        this is Exception ->
            ErrorObject(this.message)
        else ->
            ErrorObject(NetworkErrors.UNKNOWN)
    }

    Timber.e(err.toString())
    return err
}

fun HttpException.getErrorResponse(): ErrorObject {

    val response = this.response()
    var err: ErrorResponse? = null
    try {
        err = Gson().fromJson(response?.errorBody()?.string(), ErrorResponse::class.java)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return when {
        err?.message != null -> ErrorObject(err.code ?: "", err.message)
        response?.code() == 401 -> ErrorObject("Unauthorised", "Unauthorised")
        response?.code() == 500 -> ErrorObject(NetworkErrors.SERVER_ERROR)
        response?.code() == 504 -> ErrorObject(NetworkErrors.TIMEOUT)
        else -> ErrorObject(NetworkErrors.UNKNOWN)
    }
}
