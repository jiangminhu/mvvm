package com.example.baselib.util

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

object KeyBoardUtil {

    fun hideInputWindow(context: Context?) {
        if (context == null || (context !is Activity)) {
            return
        }
        val view=context.window.peekDecorView()
        if(view!=null &&  view.windowToken!=null){
            val imm=context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken,0)
        }
    }
}