package com.example.weightdemo.net

import com.example.baselib.net.ApiFactory

class ApiInstance {
    companion object{
        val instance: ApiInstance by lazy {
            ApiInstance()
        }
    }



   val apiService: ApiService by lazy {
       ApiFactory().create("",ApiService::class.java)
   }
}