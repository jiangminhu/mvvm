package com.example.baselib.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class KeyBoardUtils {
    private KeyBoardUtils() {
    }


    public static void hideInputWindow(Context context) {
        if (context == null || !(context instanceof Activity)) {
            return;
        }
        final View v = ((Activity) context).getWindow().peekDecorView();
        if (v != null && v.getWindowToken() != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

}
