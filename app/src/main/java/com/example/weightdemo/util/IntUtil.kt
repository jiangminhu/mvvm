package com.example.weightdemo.util

import android.content.Context

fun Int.dp(mContext: Context): Float {
    val scale = mContext.resources.displayMetrics.density
    return this * scale + 0.5f
}