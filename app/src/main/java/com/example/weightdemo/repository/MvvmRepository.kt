package com.example.weightdemo.repository

import com.example.baselib.repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MvvmRepository : BaseRepository() {


    suspend fun test() = flow<String> {
        delay(200)
        emit("测试网络请求")
    }.flowOn(Dispatchers.IO)
}