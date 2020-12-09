package com.example.snowman.custom_view

import android.content.Context
import android.util.AttributeSet
import com.example.snowman.R

class SnowManViewProperties(context: Context, attrs: AttributeSet?) {
    var snowManColor: Int
    var noseColor: Int
    var buttonsColor: Int
    var mouthColor: Int
    var handsColor: Int
    var shadowColor: Int

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SnowMan)
        snowManColor = typedArray.getColor(R.styleable.SnowMan_snowManColor, context.getColor(R.color.snow))
        noseColor = typedArray.getColor(R.styleable.SnowMan_noseColor, context.getColor(R.color.orange))
        buttonsColor =typedArray.getColor(R.styleable.SnowMan_buttonsColor, context.getColor(R.color.darkBrown))
        mouthColor = typedArray.getColor(R.styleable.SnowMan_mouthColor, context.getColor(R.color.red))
        handsColor = typedArray.getColor(R.styleable.SnowMan_handsColor, context.getColor(R.color.handsColor))
        shadowColor = typedArray.getColor(R.styleable.SnowMan_shadowColor, context.getColor(R.color.shadowColor))
        typedArray.recycle()
    }
}
