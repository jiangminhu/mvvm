package com.example.baselib.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.baselib.repository.BaseRepository
import com.example.baselib.util.newRepository
import kotlinx.coroutines.cancel

abstract class BaseViewModelImp<T : BaseRepository> : BaseViewModel() {

    val mRepository: T by lazy {
        newRepository()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}