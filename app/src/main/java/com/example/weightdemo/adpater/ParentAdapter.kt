package com.example.weightdemo.adpater

import BaseRecyclerAdpater
import BaseViewHolder
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weightdemo.bean.ParentBean
import com.example.weightdemo.databinding.ItemParentBinding

class ParentAdapter(context: Context, list: List<ParentBean>) :
    BaseRecyclerAdpater<ParentBean, ItemParentBinding>(context, list) {
    private val map = HashMap<Int, ChildAdapter>()

    override fun getViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ItemParentBinding {
        return ItemParentBinding.inflate(inflater, parent, false)
    }

    override fun conver(holder: BaseViewHolder<ItemParentBinding>, t: ParentBean, position: Int) {
        holder.viewBinding.tvTitle.text = t.name
        if (!map.containsKey(position)) {
            holder.viewBinding.recyclerView.layoutManager = LinearLayoutManager(mContext)
            val adapter = ChildAdapter(mContext, t.list!!)
            holder.viewBinding.recyclerView.adapter = adapter
        }
    }
}