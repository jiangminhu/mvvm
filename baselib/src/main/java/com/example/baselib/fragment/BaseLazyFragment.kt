package com.example.baselib.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.baselib.viewmodel.BaseViewModel

abstract class BaseLazyFragment<VB : BaseViewModel, DB : ViewDataBinding> :
    BaseDBFragment<VB, DB>() {
    var isVisibility = false //判断该fragment是否可见
    var isViewCreate = false
    var isAutoRefresh = false//是否自动刷新数据


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isViewCreate = true
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        isVisibility = isVisibleToUser
        if (isViewCreate && isVisibility && isAutoRefresh) {
            lazyData()
        }
    }

    /**
     * 加载数据
     */
    abstract fun lazyData()

    abstract fun initView()
}