package com.example.weightdemo.activity

import android.graphics.Color
import com.example.weightdemo.base.BaseTitleActivity
import com.example.weightdemo.databinding.ActivityThreeBinding
import com.example.weightdemo.presenter.LifPresenter
import com.example.weightdemo.util.DrawableUtil

class ThreeActivity : BaseTitleActivity<ActivityThreeBinding, LifPresenter>() {


    override fun getActivityBinding(): ActivityThreeBinding {
        return ActivityThreeBinding.inflate(layoutInflater)
    }

    override fun getPresenter(): LifPresenter {
        return LifPresenter()
    }

    override fun initData() {

    }

    override fun initView() {

        viewbind.btnOne.background =
            DrawableUtil.getRoundedDrawable(mContext, 5, "#b3e5fc", "#03a9f4", 2)


        viewbind.btnTwo.background =
            DrawableUtil.getRoundedStrokeDrawable(mContext, 5, "#26a69a", 2)
        viewbind.btnTwo.setTextColor(Color.parseColor("#26a69a"))


        val drawableClick = DrawableUtil.getRoundedFillDrawable(mContext, 5, "#03a9f4")
        val drawableNormal = DrawableUtil.getRoundedFillDrawable(mContext, 5, "#00bcd4")
        viewbind.btnThree.background =
            DrawableUtil.getSelectorDrawable(drawableClick, drawableNormal)


    }

    override fun onDestroy() {
        super.onDestroy()

    }
}