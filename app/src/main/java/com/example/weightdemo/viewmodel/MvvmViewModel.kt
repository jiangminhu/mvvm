package com.example.weightdemo.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.baselib.viewmodel.BaseViewModelImp
import com.example.weightdemo.repository.MvvmRepository

class MvvmViewModel : BaseViewModelImp<MvvmRepository>() {
    val livaData = MutableLiveData<String>()

    fun a() {
        livaData.postValue("1")
        launch({
            mRepository.test()
        }, {
            Log.e("TGA", "------result--------->$it")
        }, isShowProcess = true)
    }
}