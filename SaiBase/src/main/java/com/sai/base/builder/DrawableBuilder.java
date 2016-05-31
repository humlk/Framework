package com.sai.base.builder;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;

public class DrawableBuilder {
    private Application mContext;
    private Drawable mLeftDrawable = null;
    private Drawable mRightDrawable = null;
    private Drawable mTopDrawable = null;
    private Drawable mBottomDrawable = null;
    private int mPadding = 4;


    public DrawableBuilder(Application context) {
        this.mContext = context;
    }

    private Drawable getDrawable(int resId) {
        Drawable mDrawable = mContext.getResources().getDrawable(resId);
        mDrawable.setBounds(0, 0, mDrawable.getMinimumWidth(), mDrawable.getMinimumHeight());
        return mDrawable;
    }

    public DrawableBuilder toLeft(int resId) {
        mLeftDrawable = getDrawable(resId);
        return this;
    }

    public DrawableBuilder toRight(int resId) {
        mRightDrawable = getDrawable(resId);
        return this;
    }

    public DrawableBuilder toTop(int resId) {
        mTopDrawable = getDrawable(resId);
        return this;
    }

    public DrawableBuilder toBottom(int resId) {
        mBottomDrawable = getDrawable(resId);
        return this;
    }

    public DrawableBuilder setPadding(int padding) {
        mPadding = padding;
        return this;
    }


    public void intoCompoundButton(CompoundButton btn) {
        btn.setCompoundDrawablePadding(mPadding);
        btn.setCompoundDrawables(mLeftDrawable, mTopDrawable, mRightDrawable, mBottomDrawable);
    }

    public void clearDrawable(CompoundButton btn) {
        btn.setCompoundDrawables(null, null, null, null);
    }

    public static void main(String[] args) {
    }
}
