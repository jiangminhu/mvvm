package com.example.weightdemo.net


import retrofit2.http.GET

interface ApiService {

    @GET("http://www.baidu.com")
    suspend fun getData(): String
}