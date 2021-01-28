package com.example.weightdemo

import TestBaseAdpater
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weightdemo.base.BaseTitleActivity
import com.example.weightdemo.databinding.ActivityMainBinding
import com.example.weightdemo.presenter.BasePresenter
import kotlinx.coroutines.*

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map


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


    private suspend fun test() {
        flow {
            listOf(1, 2, 3, 4, 5).forEach {
                emit(it)
                delay(1000)
            }
        }.map {
            it + 2.0f
        }.flowOn(Dispatchers.IO)
            .map {
                val a = it + 1
                viewbind.button.text = "$it"
                a
            }
            .flowOn(Dispatchers.Main).collect {

            }


    }


    private suspend fun test1(a: Int): Int {
        coroutineScope {

        }
        delay(20000)
        return a + 100
    }
}

