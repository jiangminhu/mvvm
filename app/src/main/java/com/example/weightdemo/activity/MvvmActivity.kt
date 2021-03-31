package com.example.weightdemo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.baselib.activity.BaseActivity
import com.example.weightdemo.R
import com.example.weightdemo.databinding.ActivityMvvmBinding
import com.example.weightdemo.viewmodel.MvvmViewModel

class MvvmActivity : BaseActivity<MvvmViewModel, ActivityMvvmBinding>() {


    override fun getDataBinding(): ActivityMvvmBinding {
        return ActivityMvvmBinding.inflate(layoutInflater)
    }

    override fun showProgress(text: String?) {

    }

    override fun dismissProgress() {

    }

    override fun errorState(message: String?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        mViewModel.a()
    }
}