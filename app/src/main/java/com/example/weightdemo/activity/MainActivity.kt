package com.example.weightdemo.activity

import TestBaseAdpater
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weightdemo.base.BaseTitleActivity
import com.example.weightdemo.databinding.ActivityMainBinding
import com.example.weightdemo.presenter.BasePresenter


class MainActivity : BaseTitleActivity<ActivityMainBinding, BasePresenter>(), View.OnClickListener {
    private var mList = ArrayList<String>()
    private var mAdpater: TestBaseAdpater? = null

    companion object {
        const val CAMERA_REQUEST_CODE = 9001
        const val Album_REQUEST_CODE = 9002

    }

    private var mCameraUri: Uri? = null
    private var mCameraImagePath: String? = null

    override fun getActivityBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun getPresenter(): BasePresenter {
        return BasePresenter()
    }

    override fun initData() {
    }

    override fun initView() {
        viewbind.mainRecyclerView.layoutManager = LinearLayoutManager(mContext)
        mAdpater = TestBaseAdpater(mContext, mList)
        viewbind.mainRecyclerView.adapter = mAdpater

        for (item in 10..2000) {
            mList.add(item.toString())
        }
        mAdpater!!.notifyDataSetChanged()
        viewbind.button.setOnClickListener {
//            Toast.makeText(mContext, "这里BUG已修复", Toast.LENGTH_LONG).show()
//            Toast.makeText(mContext, "这里有BUG", Toast.LENGTH_LONG).show()
            startActivity(Intent(mContext, ThreeActivity::class.java))

        }

    }

    override fun onClick(v: View?) {

    }



}

