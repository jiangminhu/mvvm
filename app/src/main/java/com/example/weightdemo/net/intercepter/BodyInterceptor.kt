package com.example.weightdemo.net.intercepter

import okhttp3.Interceptor
import okhttp3.Response

class BodyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.body != null) {
                val body=response.code

        }
        return response
    }
}