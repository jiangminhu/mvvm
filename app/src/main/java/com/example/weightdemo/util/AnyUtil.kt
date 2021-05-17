package com.example.weightdemo.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

fun String?.isEqual(b: String?): Boolean {
    if (this == null || b == null) {
        return false
    }
    return this == b
}


