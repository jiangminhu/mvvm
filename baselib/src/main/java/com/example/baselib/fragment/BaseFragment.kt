package com.example.baselib.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.example.baselib.bean.DismissProgress
import com.example.baselib.bean.ErrorState
import com.example.baselib.bean.ShowProgress
import com.example.baselib.viewmodel.BaseViewModel

abstract class BaseFragment<VB : BaseViewModel, DB : ViewDataBinding> : Fragment() {
    private lateinit var mViewModel: VB
    private lateinit var mDataBinding: DB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDataBinding = getDataBinding(inflater, container)
        return mDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel = getViewModel()

        mViewModel.mStateLiveData.observe(this) {
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

    abstract fun getDataBinding(inflater: LayoutInflater, container: ViewGroup?): DB

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