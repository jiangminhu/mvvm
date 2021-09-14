package com.example.weightdemo.fragment

import android.content.Intent
import android.graphics.Color
import android.util.Log
import com.example.baselib.fragment.BaseLazyFragment
import com.example.weightdemo.activity.TestTitleActivity
import com.example.weightdemo.databinding.FragmentTabBinding
import com.example.weightdemo.viewmodel.NoneViewModel

class TabFragment : BaseLazyFragment<NoneViewModel, FragmentTabBinding>() {
    var page = 0


    init {
        isAutoRefresh = true
    }

    override fun preloading() {
        page = arguments?.getInt("page", 0)!!

    }

    override fun initView() {
        when (page % 3) {
            0 -> {
                mViewBinding?.run {
                    view.setBackgroundColor(Color.parseColor("#391865"))
                }
            }
            1 -> {
                mViewBinding?.run {
                    view.setBackgroundColor(Color.parseColor("#654879"))
                }
            }
            2 -> {
                mViewBinding?.run {

                    view.setBackgroundColor(Color.parseColor("#EA4978"))
                }
            }
        }
        mViewBinding?.view!!.setOnClickListener {
            startActivity(Intent(requireActivity(),TestTitleActivity::class.java))
        }
    }

    override fun initData() {
        Log.e("TGA","---------initData-------->$page")
    }

    override fun lazyData() {
        Log.e("TGA","--------lazyData--------->$page")
    }


    override fun onResume() {
        super.onResume()
        Log.e("TGA","--------onResume--------->$page")
    }

}