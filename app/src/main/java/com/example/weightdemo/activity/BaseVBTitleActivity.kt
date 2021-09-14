package com.example.weightdemo.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.example.baselib.activity.BaseActivity
import com.example.baselib.util.getViewBinding
import com.example.baselib.viewmodel.BaseViewModel
import com.example.weightdemo.databinding.ActivityBaseTitleBinding
import com.example.weightdemo.util.immersive

abstract class BaseVBTitleActivity<VB : BaseViewModel, DB : ViewBinding> :
    BaseActivity<VB, DB>() {
    private lateinit var titleBinding: ActivityBaseTitleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        titleBinding = ActivityBaseTitleBinding.inflate(layoutInflater)
        setContentView(titleBinding.rootView)
        initBar()
        mViewBinding = getViewBinding(layoutInflater, titleBinding.rootView)
        titleBinding.frameMain.addView(mViewBinding.root)
        initView()

        titleBinding.ivBack.setOnClickListener {
            Toast.makeText(this, "测试点击", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initBar() {
        immersive(titleBinding.rlTitle, true)
        if (getTitleContent().isNullOrEmpty()) {
            titleBinding.rlTitle.visibility = View.GONE
        }
    }

    override fun showProgress(text: String?) {

    }

    override fun dismissProgress() {

    }

    override fun errorState(message: String?) {

    }

    override fun tokenInvalidException() {

    }

    abstract fun initView()


    abstract fun getTitleContent(): String?



}