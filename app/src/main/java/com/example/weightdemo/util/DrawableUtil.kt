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

    fun getRoundedStrokeDrawable(
        mContext: Context,
        round: Int,
        color: String,
        width: Int
    ): Drawable {
        val shapeAppearanceModel =
            ShapeAppearanceModel.builder().setAllCorners(RoundedCornerTreatment())
                .setAllCornerSizes(round.dp(mContext))
                .build()

        return MaterialShapeDrawable(shapeAppearanceModel).apply {
            setStrokeTint(Color.parseColor(color))
            paintStyle = Paint.Style.STROKE
            strokeWidth = width.dp(mContext)
        }
    }


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


    fun getSelectorDrawable(drawableClick: Drawable, drawableNormal: Drawable): Drawable {
        val stateDrawable = StateListDrawable()
        stateDrawable.addState(intArrayOf(android.R.attr.state_pressed), drawableClick)
        stateDrawable.addState(intArrayOf(), drawableNormal)
        return stateDrawable
    }
}