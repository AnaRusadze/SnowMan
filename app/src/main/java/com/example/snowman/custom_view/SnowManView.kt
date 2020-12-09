package com.example.snowman.custom_view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


class SnowManView : View {

    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    private val nosePath = Path()
    private val mouthPath = Path()
    private val handPath = Path()
    private val snowManViewProperties: SnowManViewProperties
    private val snowManColor: Int
    private val noseColor: Int
    private val buttonsColor: Int
    private val mouthColor: Int
    private val handsColor: Int
    private val shadowColor: Int

    companion object {
        private const val FACE_RADIUS = 200f
        private const val BODY_RADIUS = 300f
        private const val EYE_RADIUS = 15f
        private const val BUTTON_RADIUS = 20f
        private const val NOSE_HEIGHT = 35f
        private const val EYE_MARGIN_HOR = 50f
        private const val HAND_MARGIN = 50f
        private const val MOUTH_HEIGHT = 50f
        private const val EYE_MARGIN_VER = 100f
        private const val BUTTON_MARGIN = 100f
        private const val MOUTH_LENGTH = 150f
        private const val NOSE_LENGTH = 200f
        private const val BODY_MARGIN = 400f
        private const val SHADOW_LENGTH = 600f
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        snowManViewProperties = SnowManViewProperties(context, attrs)
        snowManColor = snowManViewProperties.snowManColor
        noseColor = snowManViewProperties.noseColor
        buttonsColor = snowManViewProperties.buttonsColor
        mouthColor = snowManViewProperties.mouthColor
        handsColor = snowManViewProperties.handsColor
        shadowColor = snowManViewProperties.shadowColor
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawShadow(canvas)
        drawBody(canvas)
        drawEyes(canvas)
        drawNose(canvas)
        drawMouth(canvas)
        drawButtons(canvas)
        drawHands(canvas)
    }

    private fun drawBody(canvas: Canvas) {
        paint.color = snowManColor
        canvas.drawCircle(width / 2f, height / 2f, FACE_RADIUS, paint)
        canvas.drawCircle(width / 2f, height / 2f + BODY_MARGIN, BODY_RADIUS, paint)
    }

    private fun drawEyes(canvas: Canvas) {
        paint.color = buttonsColor
        canvas.drawCircle(
            width / 2f - EYE_MARGIN_VER,
            height / 2f - EYE_MARGIN_HOR,
            EYE_RADIUS,
            paint
        )
        canvas.drawCircle(
            width / 2f + EYE_MARGIN_VER,
            height / 2f - EYE_MARGIN_HOR,
            EYE_RADIUS,
            paint
        )
    }

    private fun drawNose(canvas: Canvas) {
        paint.color = noseColor
        nosePath.apply {
            moveTo(width / 2f, height / 2f) //top
            lineTo(FACE_RADIUS + NOSE_LENGTH, height / 2f)//left
            lineTo(width / 2f, height / 2f + NOSE_HEIGHT)//bottom
            //this line can be deleted because path will fill line automatically
            lineTo(width / 2f, height / 2f) //back to top
            close()
        }
        canvas.drawPath(nosePath, paint)
    }

    private fun drawMouth(canvas: Canvas) {
        paint.color = mouthColor
        mouthPath.apply {
            moveTo(width / 2f, height / 2f + 70f)
            quadTo(
                width / 2f + MOUTH_HEIGHT,
                height / 2f + MOUTH_LENGTH,
                width / 2f + EYE_MARGIN_VER,
                height / 2f + EYE_MARGIN_HOR
            )
            close()
        }
        canvas.drawPath(mouthPath, paint)
    }

    private fun drawButtons(canvas: Canvas) {
        paint.color = buttonsColor
        var count = 0
        var margin = BODY_RADIUS
        while (count < 3) {
            canvas.drawCircle(width / 2f, height / 2f + margin, BUTTON_RADIUS, paint)
            margin += BUTTON_MARGIN
            count++
        }
    }

    private fun drawShadow(canvas: Canvas) {
        paint.color = shadowColor
        val shadowRect = RectF(
            width / 2 - BODY_RADIUS,
            height / 2f + BODY_RADIUS + BODY_MARGIN - 60f,
            width / 2 - BODY_RADIUS + SHADOW_LENGTH,
            height / 2f + BODY_RADIUS + BODY_MARGIN + 20f
        )
        canvas.drawOval(shadowRect, paint)
    }


    private fun drawHands(canvas: Canvas) {
        paint.color = handsColor
        handPath.apply {
            //draw left hand
            moveTo(width / 2 - BODY_RADIUS + HAND_MARGIN, height / 2 + BODY_RADIUS)
            quadTo(
                width / 2 - BODY_RADIUS - HAND_MARGIN*2,
                height / 2f + HAND_MARGIN*2,
                width / 2 - BODY_RADIUS,
                height / 2f - HAND_MARGIN
            )
            lineTo(width / 2 - BODY_RADIUS - HAND_MARGIN, height / 2f - HAND_MARGIN)
            //draw right hand
            moveTo(width / 2 + BODY_RADIUS - HAND_MARGIN, height / 2f + BODY_RADIUS)
            quadTo(
                width / 2 + BODY_RADIUS,
                height / 2f + BODY_RADIUS- HAND_MARGIN,
                width / 2 + BODY_RADIUS+ HAND_MARGIN/2,
                height / 2f + BODY_RADIUS*2- HAND_MARGIN
            )
            lineTo(width / 2 + BODY_RADIUS+ HAND_MARGIN, height / 2f + BODY_RADIUS*2- HAND_MARGIN-20f)
            close()
        }
        canvas.drawPath(handPath, paint)
    }

}


