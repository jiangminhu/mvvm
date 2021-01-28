package com.example.weightdemo

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import com.example.weightdemo.base.BaseTitleActivity
import com.example.weightdemo.databinding.ActivitySecendBinding
import com.example.weightdemo.databinding.ActivityThreeBinding
import com.example.weightdemo.presenter.LifPresenter
import com.example.weightdemo.util.dp
import com.example.weightdemo.weight.FirstActivity
import com.google.android.material.shape.*

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
        val shapeAppearanceModel =
            ShapeAppearanceModel.builder().setAllCorners(RoundedCornerTreatment())
                .setAllCornerSizes(10.dp(mContext))
                .setRightEdge(object : TriangleEdgeTreatment(8.dp(mContext), false) {
                    override fun getEdgePath(
                        length: Float,
                        center: Float,
                        interpolation: Float,
                        shapePath: ShapePath
                    ) {
                        super.getEdgePath(length, 20
                            .dp(mContext), interpolation, shapePath)
                    }
                })
                .build()
        val backgroundDrawable = MaterialShapeDrawable(shapeAppearanceModel).apply {
            setTint(Color.parseColor("#ff0000"))
            paintStyle = Paint.Style.FILL
        }
        (viewbind.image.parent as? ViewGroup)?.clipChildren = false
        viewbind.image.setImageDrawable(backgroundDrawable)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("TGA", "ThreeActivity------------>destroy")
    }
}