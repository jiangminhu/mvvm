package com.example.weightdemo.util

import com.example.baselib.exception.ApiException
import com.example.weightdemo.base.BaseBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun String?.isEqual(b: String?): Boolean {
    if (this == null || b == null) {
        return false
    }
    return this == b
}


fun <T> BaseBean<T>.asFlow(): Flow<BaseBean<T>> {
    val a = this
    return flow {
        when (code) {
            "0000" -> {
                emit(a)
            }
            else -> {
                throw ApiException(0, "发生错误")
            }

        }
    }.flowOn(Dispatchers.IO)
}