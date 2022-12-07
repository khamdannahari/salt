package com.khamdan.nahari.data.response

sealed class Response<out R> {
    data class Success<out T>(val data: T) : Response<T>()
    data class Error(val errorMsg: String) : Response<Nothing>()
}