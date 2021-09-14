package com.example.weightdemo.weight;

import android.content.Context;
import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.WindowInsets;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.jetbrains.annotations.NotNull;

public class MyConstraintLayout extends ConstraintLayout {

    public MyConstraintLayout(@NonNull @NotNull Context context) {
        super(context);
    }

    public MyConstraintLayout(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyConstraintLayout(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyConstraintLayout(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected boolean fitSystemWindows(Rect insets) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            insets.left=0;
            insets.top=0;
            insets.right=0;
        }
        return super.fitSystemWindows(insets);
    }


    @Override
    public WindowInsets onApplyWindowInsets(WindowInsets insets) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            Insets b = null;
            b = Insets.of(0, -insets.getSystemWindowInsets().top
                    , 0, 0);
            //使两个inset.top字段相消为0
            Insets result = Insets.add(insets.getSystemWindowInsets(), b);
            WindowInsets.Builder builder=new WindowInsets.Builder(insets).setSystemWindowInsets(result);
            return super.dispatchApplyWindowInsets(builder.build());
        }
        return insets;
    }
}
