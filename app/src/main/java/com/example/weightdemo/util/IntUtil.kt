package com.example.weightdemo.util

import android.content.Context
import com.example.weightdemo.MyApplocation
import com.google.android.material.shape.CornerSize

fun Int.dp(mContext: Context): Float {
    val scale = mContext.resources.displayMetrics.density
    return this * scale + 0.5f
}