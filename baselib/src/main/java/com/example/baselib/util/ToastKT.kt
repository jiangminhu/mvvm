package com.example.baselib.util

import android.content.Context
import android.view.Gravity
import android.widget.Toast

fun Context.toast(message: String?, isShort: Boolean = true, isCenter: Boolean = false) {
    if (message.isNullOrBlank())
        return
    Toast.makeText(this, message, if (isShort) Toast.LENGTH_SHORT else Toast.LENGTH_LONG).apply {
        if (isCenter) {
            setGravity(Gravity.CENTER, 0, 0)
        } else {
            setGravity(Gravity.BOTTOM, 0, 180)
        }
    }.show()
}


fun Context.toastCenter(message: String?, isShort: Boolean = true) {
    toast(message, isShort, true)
}