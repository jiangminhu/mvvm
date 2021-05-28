package com.example.weightdemo.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.weightdemo.databinding.ActivityBaseTitleBinding
import com.example.weightdemo.presenter.BasePresenter

abstract class BaseTitleActivity<VB : ViewBinding, P : BasePresenter> : AppCompatActivity() {
    private lateinit var mViewbind: ActivityBaseTitleBinding
    lateinit var viewbind: VB
    lateinit var mPresenter: P
    lateinit var mContext: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        mViewbind = ActivityBaseTitleBinding.inflate(layoutInflater)
        setContentView(mViewbind.root)
        mContext = this
        initMData()
        initMView()
        initData()
        initView()
    }


    abstract fun getActivityBinding(): VB

    abstract fun getPresenter(): P

    private fun initMData() {
        mPresenter = getPresenter()
        lifecycle.addObserver(mPresenter)
    }


    fun setTitle(strTitle: String) {
        mViewbind.tvTitle.text = strTitle
    }


    fun setRightText(strRight: String) {
        mViewbind.tvRight.text = strRight
    }

    fun setTitleVis(isVis: Boolean) {
        if (isVis) {
            mViewbind.rlTitle.visibility = View.VISIBLE
        } else {
            mViewbind.rlTitle.visibility = View.GONE
        }
    }

    private fun initMView() {
        viewbind = getActivityBinding()
        mViewbind.frameMain.addView(viewbind.root)
    }

    abstract fun initData()

    abstract fun initView()

}
