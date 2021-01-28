package com.example.weightdemo.activity

import android.util.Log
import com.example.weightdemo.base.BaseTitleActivity
import com.example.weightdemo.databinding.ActivityConstraintlayoutBinding
import com.example.weightdemo.presenter.LifPresenter
import com.example.weightdemo.util.isEqual

class ConstraintlayoutActivity :
    BaseTitleActivity<ActivityConstraintlayoutBinding, LifPresenter>() {

    override fun getActivityBinding(): ActivityConstraintlayoutBinding {
        return ActivityConstraintlayoutBinding.inflate(layoutInflater)
    }


    override fun initData() {
        Log.e("TGA", "${"a".isEqual("a")}-------------->")
    }

    override fun initView() {
        setTitleVis(false)
    }

    override fun getPresenter(): LifPresenter {
        return LifPresenter()
    }
}
