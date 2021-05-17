package com.example.baselib.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

fun <VB : ViewBinding> Any.getViewBinding(
    inflater: LayoutInflater,
    container: ViewGroup?
): VB {
    return getViewBinding(inflater, container, false)
}

fun <VB : ViewBinding> Any.getViewBinding(
    inflater: LayoutInflater,
    container: ViewGroup?,
    isAttach: Boolean
): VB {
    val vbClass =
        (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.filterIsInstance<Class<VB>>()
    val inflate = vbClass[1].getDeclaredMethod(
        "inflate",
        LayoutInflater::class.java,
        ViewGroup::class.java,
        Boolean::class.java
    )
    return inflate.invoke(null, inflater, container, isAttach) as VB
}