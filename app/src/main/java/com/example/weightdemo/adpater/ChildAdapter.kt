package com.example.weightdemo.adpater

import com.example.baselib.adapter.BaseRecyclerAdapter
import com.example.baselib.adapter.BaseViewHolder
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.weightdemo.databinding.ItemChildBinding

class ChildAdapter(context: Context, list: List<String>) :
    BaseRecyclerAdapter<String, ItemChildBinding>(context, list) {
    override fun getViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ItemChildBinding {
        return ItemChildBinding.inflate(inflater, parent, false)
    }

    override fun conver(holder: BaseViewHolder<ItemChildBinding>, t: String, position: Int) {
        holder.viewBinding.tvContent.text = t
    }
}