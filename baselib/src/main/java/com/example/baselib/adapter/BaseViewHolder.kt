package com.example.baselib.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class BaseViewHolder<V : ViewBinding> constructor(val viewBinding: V) : RecyclerView.ViewHolder(viewBinding.root) {

}