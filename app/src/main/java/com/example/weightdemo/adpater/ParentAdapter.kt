package com.example.weightdemo.adpater

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baselib.adapter.BaseRecyclerAdapter
import com.example.baselib.adapter.BaseViewHolder
import com.example.weightdemo.bean.ParentBean
import com.example.weightdemo.databinding.ItemParentBinding

class ParentAdapter(context: Context, list: List<ParentBean>) :
    BaseRecyclerAdapter<ParentBean, ItemParentBinding>(context, list) {
    private val map = HashMap<Int, ChildAdapter>()


    override fun convert(holder: BaseViewHolder<ItemParentBinding>, t: ParentBean, position: Int) {
        holder.viewBinding.tvTitle.text = t.name
        if (!map.containsKey(position)) {
            holder.viewBinding.recyclerView.layoutManager = LinearLayoutManager(mContext)
            val adapter = ChildAdapter(mContext, t.list!!)
            holder.viewBinding.recyclerView.adapter = adapter
        }
    }

}