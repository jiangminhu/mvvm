package com.example.baselib.bean

data class HttpResponse<T>(
    val IsSuccess: Boolean = false,
    val Code: Int = 0,
    val Message: String? = null,
    val RespTime: String? = null,
    val data: T?
)