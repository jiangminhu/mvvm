package com.example.weightdemo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weightdemo.R
import com.example.weightdemo.adpater.ParentAdapter
import com.example.weightdemo.bean.ParentBean

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private val mList = ArrayList<ParentBean>()
    private lateinit var mAdapter: ParentAdapter
    private lateinit var layoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        recyclerView = findViewById(R.id.recycler_view)
        layoutManager= LinearLayoutManager(this)
        recyclerView.layoutManager =layoutManager
        mAdapter = ParentAdapter(this, mList)
        recyclerView.adapter = mAdapter

        initData()
    }


    private fun initData() {
        for (i in 1..2000) {
            val parentBean = ParentBean()
            parentBean.name = i.toString()
            val tempList = ArrayList<String>()
            val max = Math.random() * 5+1
            for (k in 1..max.toInt()) {
                tempList.add("$i-->$k")
            }
            parentBean.list = tempList
            mList.add(parentBean)
        }
        layoutManager.scrollToPosition(mList.size/2)
//        mAdapter.notifyDataSetChanged()


    }

}