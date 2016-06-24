package com.sai.ui.behavior;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;

import com.sai.ui.util.ViewUtil;

public class FabUpDownBehavior extends FabBehavior{

    public FabUpDownBehavior(Context context, AttributeSet attr){
        super(context, attr);
    }

    private boolean mIsAnimating = false;
    private long mDuration = 300;
    private float mTranslationY;

    @Override
    public void animateIn(final FloatingActionButton floatingActionButton) {
//        if(mIsAnimating || mTranslationY == 0){
//            return;
//        }
        //正在从底部进入或者已经正常显示了就不进行操作了
        if(mIsAnimating || floatingActionButton.getVisibility() == View.VISIBLE){
            return;
        }

        //从底部进入
        ObjectAnimator animator = ObjectAnimator.ofFloat(floatingActionButton,"translationY", 0);
        animator.setDuration(mDuration);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mIsAnimating = true;
                ViewUtil.showView(floatingActionButton);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mIsAnimating = false;
            }
        });
        animator.start();
    }

    @Override
    public void animateOut(final FloatingActionButton floatingActionButton) {
        //从底部出去
        //正在滑向底部或者已经滑出底部了就不进行操作
        if(mIsAnimating || floatingActionButton.getVisibility() == View.GONE){
            return;
        }
        mTranslationY = floatingActionButton.getHeight();
        ObjectAnimator animator = ObjectAnimator.ofFloat(floatingActionButton,"translationY", mTranslationY);
        animator.setDuration(mDuration);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mIsAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ViewUtil.hideView(floatingActionButton);
                mIsAnimating = false;
            }
        });
        animator.start();
    }
}
