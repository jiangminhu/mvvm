package com.example.baselib.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.baselib.util.getViewBinding
import com.example.baselib.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType

abstract class BaseDBFragment<VB : BaseViewModel, DB : ViewBinding> : Fragment() {
    protected lateinit var mViewModel: VB
    protected var mViewBinding: DB? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewBinding = getViewBinding(inflater, container)
        return mViewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel = getViewModel()
        preloading()
        initView()
        initData()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mViewBinding = null
    }

    private fun getViewModel(): VB {
        val types = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
        return ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
            .create(types[0] as Class<VB>)
    }

    protected open fun preloading() {

    }

    abstract fun initView()

    abstract fun  initData()

}