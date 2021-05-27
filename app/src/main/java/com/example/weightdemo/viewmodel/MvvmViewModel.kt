package com.example.weightdemo.viewmodel

import android.util.Log
import com.example.baselib.viewmodel.BaseViewModelImp
import com.example.weightdemo.repository.MvvmRepository
import kotlinx.coroutines.flow.collect

class MvvmViewModel : BaseViewModelImp<MvvmRepository>() {


    fun a() {
        launch({
            mRepository.test().collect {
                Log.e("TAG", "${it.code}------------->${it.data}")
            }
        }, { code, message ->
            Log.e("TAG", "$code------------->${message}")
        }, true)
        Log.e("tga"," --------------------> ")

    }
}