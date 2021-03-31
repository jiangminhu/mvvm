package com.example.baselib.viewmodel

import com.example.baselib.repository.BaseRepository

abstract class BaseViewModelImp<T : BaseRepository> : BaseViewModel() {

    val mRepository: T by lazy {
        getRepository()
    }


    abstract fun getRepository(): T

}