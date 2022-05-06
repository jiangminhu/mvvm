package com.example.baselib.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.baselib.bean.DismissProgress
import com.example.baselib.bean.ErrorState
import com.example.baselib.bean.ShowProgress
import com.example.baselib.bean.TokenInvalidException
import com.example.baselib.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<VB : BaseViewModel, DB : ViewBinding> : AppCompatActivity() {
    protected lateinit var mViewModel: VB
    protected lateinit var mViewBinding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding=getViewBinding()
        mViewModel = getViewModel()
        setContentView(mViewBinding.root)
        mViewModel.mStateLiveData.observe(this) {
            when (it) {
                is ShowProgress -> {
                    showProgress(it.message)
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
                is TokenInvalidException -> {
                    tokenInvalidException()
                }
            }
        }

    }

    private fun getViewModel(): VB {
        val types = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
        return ViewModelProvider.AndroidViewModelFactory(application).create(types[0] as Class<VB>)
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissProgress()
    }

//    abstract fun getViewBinding(
//        layoutInflater: LayoutInflater,
//        parent: ViewGroup,
//        isAttach: Boolean
//    ): DB

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

    abstract fun tokenInvalidException()

    abstract fun getViewBinding(): DB
}