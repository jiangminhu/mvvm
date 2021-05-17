package com.example.weightdemo.weight.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import androidx.annotation.StyleRes
import com.example.weightdemo.R

class LoadingDialog constructor(context: Context, @StyleRes themeResId: Int) :
    Dialog(context, themeResId) {

    constructor(context: Context) : this(context, R.style.dialog)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_loading)

        setCanceledOnTouchOutside(false)
    }
}