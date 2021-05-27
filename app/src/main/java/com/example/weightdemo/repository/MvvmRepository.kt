package com.example.weightdemo.repository

import com.example.baselib.repository.BaseRepository
import com.example.weightdemo.net.ApiInstance
import com.example.weightdemo.util.asFlow

class MvvmRepository : BaseRepository() {


    suspend fun test() =  ApiInstance.instance.apiService.getData().asFlow()




}