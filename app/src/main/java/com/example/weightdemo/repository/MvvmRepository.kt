package com.example.weightdemo.repository

import com.example.baselib.bean.HttpResponse
import com.example.baselib.repository.BaseRepository
import kotlinx.coroutines.delay

class MvvmRepository : BaseRepository() {


    suspend fun test() :HttpResponse<Int>? {
        delay(3000)
       return null
    }


}