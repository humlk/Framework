package com.sai.ui.view.nest;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 嵌套滑动view
 */
public class NestedScrollingLayout extends FrameLayout implements NestedScrollingParent, NestedScrollingChild {

    private String TAG = "NestedScrollingLayout";

    /**
     * parent的帮助类，用于和child交互
     **/
    private NestedScrollingParentHelper mParentHelper;
    /** child的代理类，用来和parent交互 **/
    private NestedScrollingChildHelper mChildHelper;

    public NestedScrollingLayout(Context context) {
        this(context, null);
    }

    public NestedScrollingLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NestedScrollingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mParentHelper = new NestedScrollingParentHelper(this);
        mChildHelper = new NestedScrollingChildHelper(this);
    }


    /*----------------------------NestedScrollingParent----------------------------------*/
    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        //如果child是纵向滑动的话也参与到child的滑动事件中
        Log.d(TAG, "onStartNestedScroll");
        return isEnabled() && (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        //onStartNestedScroll 返回true，那parent就进入这里
        mParentHelper.onNestedScrollAccepted(child, target, nestedScrollAxes);
        Log.d(TAG, "onNestedScrollAccepted");
    }

    @Override
    public void onStopNestedScroll(View target) {
        mParentHelper.onStopNestedScroll(target);
        Log.d(TAG, "onStopNestedScroll");
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        //child滑动后parent再滑动
        Log.d(TAG, String.format("onNestedScroll(%d,%d,%d,%d) ", dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed));

        dispatchNestedScroll(dxConsumed,dyConsumed,dxUnconsumed,dyUnconsumed, null);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        //child滑动前调用，询问parent是否要参与滑动，参与的话就修改consumed。其实就是先于child滑动
        Log.d(TAG, String.format("onNestedPreScroll(%d,%d,%d,%d) ", dx, dy, consumed[0], consumed[1]));
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        return mParentHelper.getNestedScrollAxes();
    }



    /*----------------------NestedScrollingChild 做为child时的处理-------------------------*/

    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        mChildHelper.setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return mChildHelper.isNestedScrollingEnabled();
    }

    @Override
    public boolean startNestedScroll(int axes) {
        return mChildHelper.startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll() {
        mChildHelper.stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent() {
        return mChildHelper.hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        return mChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return mChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return mChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return mChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }

    //
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        //单点触控检测
////        int action = ev.getAction();
//        // 多点触控检测
//        int action = MotionEventCompat.getActionMasked(ev);
//
//        if (!isEnabled()) {
//            return false;
//        }
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//
//                break;
//            case MotionEvent.ACTION_MOVE:
//
//                break;
//
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_CANCEL:
//
//                break;
//        }
//
//        return super.onInterceptTouchEvent(ev);
//    }

    /** 单指触摸 活动手指**/
    private int mActivePointerId = -1;
    /** 最后移动的y坐标 **/
    private int mLastMotionY;
    /** 记录parent滑动距离 **/
    private int[] mConsumed;
    /** 记录child位移 **/
    private int[] mOffsetInWindow;
    /** child 滑动偏移 **/
    private int mNestedYOffset;
    /** 是否在移动 **/
    private boolean mIsBeingDragged;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 重新拷贝一个新的对象进行处理
        MotionEvent vtev = MotionEvent.obtain(event);

        //单点触控检测
//        int action = ev.getAction();
        // 多点触控检测
        int action = MotionEventCompat.getActionMasked(vtev);

        if (!isEnabled()) {
            return false;
        }
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mIsBeingDragged = false;
                mActivePointerId = MotionEventCompat.getPointerId(vtev,0);
                mLastMotionY = (int)vtev.getY();
                //准备开始滑动。默认只支持纵向滑动
                startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL);
                break;
            case MotionEvent.ACTION_MOVE:
                //获取当前活动手指索引
                int currentPointIndex = MotionEventCompat.getActionIndex(vtev);
                //获取当前活动手指Id
                int currentPointId = MotionEventCompat.getPointerId(vtev,currentPointIndex);

                //获取触摸手指
                int thisPointerIndex = MotionEventCompat.findPointerIndex(vtev, mActivePointerId);
                if(thisPointerIndex == -1){
                    break;
                }

                //获取移动距离
                int y = (int)MotionEventCompat.getY(vtev,thisPointerIndex);

                //纵向滑动距离
                int deltaY = y - mLastMotionY;

                //判断滑动方向
                if(deltaY > 0){
                    // 向上滑动
                }else{
                    //向下滑动
                }

                //child 准备分发滑动事件
                boolean result = dispatchNestedPreScroll(0, deltaY, mConsumed, mOffsetInWindow);
                if(result){//parent 也参与滑动
                    //重新修正child滑动距离, child实际滑动距离 = 总的纵向滑动距离 - parent滑动距离
                    deltaY = deltaY - mConsumed[1];
//                    vtev.setLocation(0,deltaY);
                    //坐标同步, 这个再细细研究下是怎么让物理坐标和逻辑坐标同步的。
                    vtev.offsetLocation(0, mOffsetInWindow[1]);
                    mNestedYOffset += mOffsetInWindow[1];
                }else{//parent 没有滑动

                }

                if(!mIsBeingDragged){
                    mIsBeingDragged = true;
                }

                if (mIsBeingDragged) {
                    mLastMotionY = y - mOffsetInWindow[1];
                }

//                mLastMotionY = y;

                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mIsBeingDragged = false;
                stopNestedScroll();
                break;
        }
        vtev.recycle();
        return true;
    }
}
