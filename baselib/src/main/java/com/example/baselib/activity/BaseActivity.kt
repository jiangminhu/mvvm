package com.example.baselib.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.observe
import com.example.baselib.bean.DismissProgress
import com.example.baselib.bean.ErrorState
import com.example.baselib.bean.ShowProgress
import com.example.baselib.viewmodel.BaseViewModel

abstract class BaseActivity<VB : BaseViewModel, DB : ViewDataBinding> : AppCompatActivity() {
    private lateinit var mViewModel: VB
    private lateinit var mDataBinding: DB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBinding = getDataBinding()
        mViewModel = getViewModel()

        mViewModel.mStateLiveData.observe(this){
            when (it) {
                is ShowProgress -> {
                    showProgress(null)
                }
                is DismissProgress -> {
                    dismissProgress()
                }
                is ErrorState -> {
                    dismissProgress()
                    it.message?.apply {
                        errorState(this)
                    }
                }
            }
        }
    }


    abstract fun getViewModel(): VB

    abstract fun getDataBinding(): DB

    /**
     * 加载对话框
     */
    abstract fun showProgress(text: String?)

    /**
     * 隐藏对话框
     */
    abstract fun dismissProgress()

    /**
     * 处理带消息的错误
     */
    abstract fun errorState(message: String?)
}