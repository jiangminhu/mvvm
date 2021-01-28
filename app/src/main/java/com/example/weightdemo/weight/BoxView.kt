package com.example.weightdemo.weight

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.view.View
import com.example.weightdemo.R
import com.example.weightdemo.bean.BoxBean
import kotlin.math.max

class BoxView : View {
    var mPaint = Paint()
    var mList = ArrayList<BoxBean>()
    var lineWidth = 6

    constructor(mContext: Context) : super(mContext) {
        val context = mContext
    }

    constructor(mContext: Context, mAttributeSet: AttributeSet) : super(mContext, mAttributeSet) {
        initP()
        val context = mContext
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val minimumWidth = suggestedMinimumWidth;
        val minimumHeight = suggestedMinimumHeight;
        var width = getWidth(minimumWidth, widthMeasureSpec)
        var height = getHeight(minimumHeight, heightMeasureSpec)
        setMeasuredDimension(width, height)
    }


    private fun getWidth(defaultWidth: Int, measureMode: Int): Int {
        var specMode = MeasureSpec.getMode(measureMode)
        var specSize = MeasureSpec.getSize(measureMode)
        var width = defaultWidth
        when (specMode) {
            MeasureSpec.AT_MOST -> {
                width = paddingLeft + paddingRight;
            }
            MeasureSpec.EXACTLY -> width = specSize

            MeasureSpec.UNSPECIFIED -> width = max(width, specSize)
        }

        return width
    }

    private fun getHeight(defaultHeight: Int, measureMode: Int): Int {
        var specMode = MeasureSpec.getMode(measureMode)
        var specSize = MeasureSpec.getSize(measureMode)
        var height = defaultHeight
        when (specMode) {
            MeasureSpec.AT_MOST -> {
                height = paddingTop + paddingBottom;
            }
            MeasureSpec.EXACTLY -> height = specSize

            MeasureSpec.UNSPECIFIED -> height = max(height, specSize)
        }

        return height
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawBox(canvas!!)
    }


    private fun initP() {
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.FILL

        for (index in 21 downTo 1) {
            var boxBean = BoxBean()
            boxBean.ScreenCol = 3
            boxBean.ScreenRow = 7
            boxBean.CellSN = 7
            mList.add(boxBean)
        }
    }

    private fun drawBox(canvas: Canvas) {
        for ((index, item) in mList.withIndex()) {
            var position = index.plus(1)
            var w = width.div(item.ScreenCol)
                .minus(lineWidth)
            var h = height.div(item.ScreenRow)
                .minus(lineWidth)
            var row = getRow(position, item.ScreenRow)
            var col = getCol(position, item.ScreenRow)
            var rect = Rect()
            rect.left = w.times(col.minus(1)).plus(col.minus(1).times(lineWidth))
            rect.top = h.times(row.minus(1)).plus(row.minus(1).times(lineWidth))
            rect.right = rect.left.plus(w)
            rect.bottom = rect.top.plus(h)
            if (position == item.CellSN) {
                mPaint.color = Color.YELLOW
            } else {
                mPaint.color = Color.WHITE
            }
            canvas.drawRect(rect, mPaint)
            if (row == item.Row && col == item.Col) {
                var bitmap = BitmapFactory.decodeResource(resources, R.mipmap.icon_dp)
                var matrix = Matrix()
                matrix.postScale(
                    h.div(bitmap.height.plus(10).toFloat()),
                    h.div(bitmap.height.plus(10).toFloat())
                )
                var bitmapNew = Bitmap.createBitmap(
                    bitmap, 0, 0, bitmap.width,
                    bitmap.height, matrix, false
                )
                canvas.drawBitmap(
                    bitmapNew,
                    rect.left.toFloat().plus(w.div(2).minus(bitmapNew.width.div(2))),
                    rect.top.toFloat().plus(h.div(2).minus(bitmapNew.height.div(2))),
                    mPaint
                )
            }


        }
    }


    private fun getRow(position: Int, scressRow: Int): Int {
        return if (position.rem(scressRow) == 0) scressRow else position.rem(
            scressRow
        )
    }

    private fun getCol(position: Int, scressRow: Int): Int {
        return if (position.rem(scressRow) == 0) position.div(scressRow) else position.div(scressRow)
            .plus(1)
    }
}