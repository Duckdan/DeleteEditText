package com.study.yang.deleteedittext

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.AppCompatEditText
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DefineEditTextKotlin : AppCompatEditText, TextWatcher, View.OnTouchListener {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, R.attr.editTextStyle)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, 0)

    override fun onFinishInflate() {
        super.onFinishInflate()
        //设置文本内容变化监听
        addTextChangedListener(this)
        //设置触摸事件
        setOnTouchListener(this)
    }

    override fun afterTextChanged(s: Editable?) {
        val length = s?.length
        var rightDrawable: Drawable? = null
        if (length!! > 0) {
            compoundDrawablePadding = 5
            rightDrawable = resources.getDrawable(R.drawable.btn_delete_24dp)
            rightDrawable!!.setBounds(0, 0, rightDrawable.minimumWidth, rightDrawable.minimumHeight) //设置边界

        }
        setCompoundDrawables(null, null, rightDrawable, null)
    }

    override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        val drawable = compoundDrawables[2] ?: return false
        if (event?.action != MotionEvent.ACTION_UP) {
            return false
        }

        if (event?.x > width - paddingRight - drawable?.intrinsicWidth) {
            setText("")
        }

        return false
    }

}