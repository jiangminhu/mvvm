package com.example.weightdemo.viewmodel

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewModelScope
import com.example.baselib.viewmodel.BaseViewModelImp
import com.example.weightdemo.repository.MvvmRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MvvmViewModel : BaseViewModelImp<MvvmRepository>() {
    val livaData = MutableLiveData<String>()
    val sharedFlow=MutableSharedFlow<String>()
    fun a() {
        livaData.postValue("1")
        launch({
            mRepository.test()
        }, {
            Log.e("TGA", "------result--------->$it")
        }, isShowProcess = true)
    }


    fun b(lifecycle: Lifecycle) {
        viewModelScope.launch {
            flow {
                emit(1)
            }.flowWithLifecycle(lifecycle, Lifecycle.State.RESUMED)
                .collect {
                    println("------------>${it}")
                }
        }
    }
}