package com.aplikasi.mvvmloginretrofit.api

data class NetworkResult<out T>(val state: State, val data: T?, val message: String?, val loading :Boolean = false) {

    companion object {

        fun <T> success(data: T?): NetworkResult<T> {
            return NetworkResult(State.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): NetworkResult<T> {
            return NetworkResult(State.ERROR, data, msg)
        }

        fun <T> loading(data: T?): NetworkResult<T> {
            return NetworkResult(State.LOADING, data, null, loading = true)
        }

    }
}
