package com.example.weightdemo.activity

import android.os.Bundle
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.example.baselib.activity.BaseActivity
import com.example.baselib.util.getViewBinding
import com.example.baselib.viewmodel.BaseViewModel
import com.example.weightdemo.R
import com.example.weightdemo.databinding.ActivityBaseTitleBinding
import com.zackratos.ultimatebarx.ultimatebarx.UltimateBarX

abstract class BaseVBTitleActivity<VB : BaseViewModel, DB : ViewBinding> :
    BaseActivity<VB, DB>() {
    private lateinit var titleBinding: ActivityBaseTitleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UltimateBarX.with(this).colorRes(R.color.colorAccent).fitWindow(true).light(false)
            .applyStatusBar()
        UltimateBarX.get(this).light(true).applyStatusBar()
        titleBinding = ActivityBaseTitleBinding.inflate(layoutInflater)
        setContentView(titleBinding.rootView)
        mViewBinding = getViewBinding(layoutInflater, titleBinding.rootView)
        titleBinding.frameMain.addView(mViewBinding?.root)
        initView()

        titleBinding.ivBack.setOnClickListener {
            Toast.makeText(this, "测试点击", Toast.LENGTH_SHORT).show()
        }
    }

    override fun showProgress(text: String?) {

    }

    override fun dismissProgress() {

    }

    override fun errorState(message: String?) {

    }

    abstract fun initView()
}