package com.study.yang.deleteedittext;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DefineEditText extends AppCompatEditText implements TextWatcher, View.OnTouchListener {
    public DefineEditText(Context context) {
        this(context, null);
    }

    public DefineEditText(Context context, AttributeSet attrs) {
        //在xml文件中使用当前控件时，将会调用此构造函数，此处必须传入R.attr.editTextStyle
        //否则自定义控件将会丧失EditText自身的属性
        this(context, attrs, R.attr.editTextStyle);
    }

    public DefineEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        /**
         * 将事件再注册给控件本身，否则控件将会丧失自定义的效果
         */
        addTextChangedListener(this);
        setOnTouchListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        int length = s.length();
        Drawable rightDrawable = null;
        if (length > 0) {
            setCompoundDrawablePadding(5);
            rightDrawable = getResources().getDrawable(R.drawable.btn_delete_24dp);
            rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight()); //设置边界

        }
        setCompoundDrawables(null, null, rightDrawable, null);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // et.getCompoundDrawables()得到一个长度为4的数组，分别表示左右上下四张图片
        Drawable drawable = getCompoundDrawables()[2];
        //如果右边没有图片，不再处理
        if (drawable == null) return false;
        //如果不是按下事件，不再处理
        if (event.getAction() != MotionEvent.ACTION_UP) return false;
        if (event.getX() > getWidth() - getPaddingRight() - drawable.getIntrinsicWidth()) {
            setText("");
        }
        return false;
    }
}
