package com.example.weightdemo.net


import com.example.weightdemo.base.BaseBean
import com.example.weightdemo.base.TestBean
import retrofit2.http.GET

interface ApiService {

    @GET("http://www.baidu.com")
    suspend fun getData(): BaseBean<TestBean>
}