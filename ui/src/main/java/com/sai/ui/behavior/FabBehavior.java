package com.sai.ui.behavior;


import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import com.sai.ui.animation.ViewAnimator;

/**
 *
 */
public class FabBehavior extends FloatingActionButton.Behavior implements ViewAnimator<FloatingActionButton>{

    public FabBehavior(Context context, AttributeSet attr){
        super();
    }

    /**
     * target 滚动了
     * @param coordinatorLayout
     * @param child
     * @param directTargetChild
     * @param target
     * @param nestedScrollAxes
     * @return
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {

        //纵向滑动
        return nestedScrollAxes== ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    /**
     *
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dxConsumed child滑动的距离
     * @param dyConsumed
     * @param dxUnconsumed
     * @param dyUnconsumed
     */
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        //向上滑动
        if(dyConsumed > 0){
            animateOut(child);
        }else if(dyConsumed < 0){
            animateIn(child);
        }
    }

    @Override
    public void animateIn(FloatingActionButton floatingActionButton) {

    }

    @Override
    public void animateOut(FloatingActionButton floatingActionButton) {

    }
}
