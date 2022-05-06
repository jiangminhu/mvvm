package com.example.weightdemo.activity

import android.widget.Toast
import com.example.weightdemo.databinding.ActivityTestTitleBinding
import com.example.weightdemo.viewmodel.MvvmViewModel

class TestTitleActivity : BaseVBTitleActivity<MvvmViewModel, ActivityTestTitleBinding>() {


    override fun initView() {
        mViewBinding.iv.setOnClickListener {
            Toast.makeText(this, "测试点击", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun getTitleContent(): String? {
         return "测试"
    }

    override fun getViewBinding(): ActivityTestTitleBinding {
         return ActivityTestTitleBinding.inflate(layoutInflater)
    }


}