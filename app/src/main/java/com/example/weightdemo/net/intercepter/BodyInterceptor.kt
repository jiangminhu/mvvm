package com.example.weightdemo.net.intercepter

import com.example.baselib.exception.ApiException
import okhttp3.Interceptor
import okhttp3.Response

class BodyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.body != null) {
            val code = response.code
            if (code == 500) {
                throw  ApiException(response.code, "服务器异常")
            }
        }
        return response
    }
}