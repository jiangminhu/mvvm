package com.example.weightdemo.activity

import android.content.Intent
import android.util.Log
import com.example.weightdemo.base.BaseTitleActivity
import com.example.weightdemo.databinding.ActivitySecendBinding
import com.example.weightdemo.presenter.LifPresenter

class SecendActivity : BaseTitleActivity<ActivitySecendBinding, LifPresenter>() {


    override fun getActivityBinding(): ActivitySecendBinding {
        return ActivitySecendBinding.inflate(layoutInflater)
    }

    override fun getPresenter(): LifPresenter {
        return LifPresenter()
    }

    override fun initData() {
        viewbind.button2.setOnClickListener {
            var intent = Intent(mContext, ThreeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP )
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

    override fun initView() {

    }


    override fun onDestroy() {
        super.onDestroy()
        Log.e("TGA","SecendActivity------------>destroy")
    }
}