package com.example.baselib.viewmodel

import com.example.baselib.repository.BaseRepository
import com.example.baselib.util.newRepository

abstract class BaseViewModelImp<T : BaseRepository> : BaseViewModel() {

    val mRepository: T by lazy {
       newRepository()
    }
}