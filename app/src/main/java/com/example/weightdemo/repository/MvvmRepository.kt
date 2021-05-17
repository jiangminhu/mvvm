package com.example.weightdemo.repository

import com.example.baselib.repository.BaseRepository
import com.example.weightdemo.net.ApiInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MvvmRepository : BaseRepository() {


    suspend fun test() = flow {
        kotlinx.coroutines.delay(5000)
        emit(ApiInstance.instance.apiService.getData())
    }.flowOn(Dispatchers.IO)


    suspend fun test2() = ApiInstance.instance.apiService.getData()
}