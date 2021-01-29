package com.example.weightdemo.util

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import com.google.android.material.shape.*

/**
 * 自动生成圆角图片
 */
object DrawableUtil {

    /**
     * 填充圆角背景图
     * round: 圆角度
     * color：填充颜色
     */
    fun getRoundedFillDrawable(mContext: Context, round: Int, color: String): Drawable {
        val shapeAppearanceModel =
            ShapeAppearanceModel.builder().setAllCorners(RoundedCornerTreatment())
                .setAllCornerSizes(round.dp(mContext))
                .build()

        return MaterialShapeDrawable(shapeAppearanceModel).apply {
            setTint(Color.parseColor(color))
            paintStyle = Paint.Style.FILL
        }
    }

    /**
     * 非填充圆角背景图
     * round: 圆角度
     * color：填充颜色
     * lineWidth：线粗细
     */
    fun getRoundedStrokeDrawable(
        mContext: Context,
        round: Int,
        color: String,
        lineWidth: Int
    ): Drawable {
        val shapeAppearanceModel =
            ShapeAppearanceModel.builder().setAllCorners(RoundedCornerTreatment())
                .setAllCornerSizes(round.dp(mContext))
                .build()

        return MaterialShapeDrawable(shapeAppearanceModel).apply {
            setStrokeTint(Color.parseColor(color))
            paintStyle = Paint.Style.STROKE
            strokeWidth = lineWidth.dp(mContext)
        }
    }


    /**
     * 混合背景图
     * round: 圆角度
     * fillColor：填充颜色
     * strokeColor：边线颜色
     * mStrokeWidth：边线粗细
     */
    fun getRoundedDrawable(
        mContext: Context,
        round: Int,
        fillColor: String,
        strokeColor: String,
        mStrokeWidth: Int
    ): Drawable {
        val shapeAppearanceModel =
            ShapeAppearanceModel.builder().setAllCorners(RoundedCornerTreatment())
                .setAllCornerSizes(round.dp(mContext))
                .build()

        return MaterialShapeDrawable(shapeAppearanceModel).apply {
            setStrokeTint(Color.parseColor(strokeColor))
            paintStyle = Paint.Style.FILL_AND_STROKE
            strokeWidth = mStrokeWidth.dp(mContext)
            setTint(Color.parseColor(fillColor))
        }
    }


    /**
     * 按钮背景选择图
     * drawableClick：按下时背景图
     * drawableNormal：正常背景图
     */
    fun getSelectorDrawable(drawableClick: Drawable, drawableNormal: Drawable): Drawable {
        val stateDrawable = StateListDrawable()
        stateDrawable.addState(intArrayOf(android.R.attr.state_pressed), drawableClick)
        stateDrawable.addState(intArrayOf(), drawableNormal)
        return stateDrawable
    }
}