package com.example.weightdemo.adpater

import android.content.Context
import com.example.baselib.adapter.BaseRecyclerAdapter
import com.example.baselib.adapter.BaseViewHolder
import com.example.weightdemo.databinding.ItemChildBinding

class ChildAdapter(context: Context, list: List<String>) :
    BaseRecyclerAdapter<String, ItemChildBinding>(context, list) {


    override fun convert(holder: BaseViewHolder<ItemChildBinding>, t: String, position: Int) {
        holder.viewBinding.tvContent.text = t
    }
}