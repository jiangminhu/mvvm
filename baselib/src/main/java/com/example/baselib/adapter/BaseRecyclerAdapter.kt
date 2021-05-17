package com.example.baselib.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.baselib.util.getViewBinding

abstract class BaseRecyclerAdapter<T, V : ViewBinding> constructor(
    content: Context,
    data: List<T>
) : RecyclerView.Adapter<BaseViewHolder<V>>() {
    val mContext: Context = content
    val mData: List<T> = data
    lateinit var viewHolder: BaseViewHolder<V>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<V> {
        val inflater = LayoutInflater.from(mContext)
        return BaseViewHolder(getViewBinding(inflater, parent))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<V>, position: Int) {
        convert(holder, mData[position], position)
    }


    abstract fun convert(holder: BaseViewHolder<V>, t: T, position: Int)
}