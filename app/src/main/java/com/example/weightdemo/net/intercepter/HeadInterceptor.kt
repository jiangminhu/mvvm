package com.example.weightdemo.net.intercepter

import okhttp3.Interceptor
import okhttp3.Response

class HeadInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader("token", "this is token").build()
        return chain.proceed(request)
    }
}