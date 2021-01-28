package com.example.weightdemo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weightdemo.R
import com.example.weightdemo.adpater.ParentAdapter
import com.example.weightdemo.bean.ParentBean

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private val mList = ArrayList<ParentBean>()
    private lateinit var mAdapter: ParentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        recyclerView = findViewById(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = ParentAdapter(this, mList)
        recyclerView.adapter = mAdapter

        initData()
    }


    private fun initData() {
        for (i in 1..20) {
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

        mAdapter.notifyDataSetChanged()

    }
}