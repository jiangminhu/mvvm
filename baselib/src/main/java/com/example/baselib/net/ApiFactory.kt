package com.example.baselib.net

import com.example.baselib.BuildConfig
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Retrofit 工具类
 */
class ApiFactory {
    companion object {
        /**
         * 连接超时时间
         */
        const val CONNECTTIMEOUT: Long = 15

        /**
         * 读取超时时间
         */
        const val READTIMEOUT: Long = 10

        /**
         * 写入超时时间
         */
        const val WRITETIMEOUT: Long = 10
    }

    //日志拦截器
    private val mLoggingInterceptor: Interceptor by lazy {
        HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
    }


    //创建retrofit
    fun <T> create(baseUrl: String, clasz: Class<T>, vararg interceptor: Interceptor): T =
        Retrofit.Builder().baseUrl(baseUrl).client(newClient(*interceptor))
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).build().create(clasz)

    //配置OKHttp
    private fun newClient(vararg interceptors: Interceptor): OkHttpClient =
        OkHttpClient.Builder().apply {
            connectTimeout(CONNECTTIMEOUT, TimeUnit.SECONDS)
            readTimeout(READTIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITETIMEOUT, TimeUnit.SECONDS)
            interceptors.forEach {
                addInterceptor(it)
            }
            if (BuildConfig.DEBUG) {
                addInterceptor(mLoggingInterceptor)
            }
        }.build()

    fun test() {
        val requestBody = "".toRequestBody("application/json;charset=utf-8".toMediaType())
    }
}