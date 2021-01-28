package com.example.weightdemo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    var isVisibility = false //判断该fragment是否可见
    var isViewCreate = false
    var isAutoRefresh = false//是否自动刷新数据

    lateinit var viewBinding: VB


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = getViewBind(inflater, container)
        isViewCreate = true
        return viewBinding.root
    }

    /**
     * 获取viewbind
     */
    abstract fun getViewBind(inflater: LayoutInflater, container: ViewGroup?): VB


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