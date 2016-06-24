package com.sai.ui.behavior;


import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import com.sai.ui.R;

public class BehaviorView <V extends View> extends CoordinatorLayout.Behavior<V>{

    private int mTargetId;

    /**
     * 该方法是必须的
     * Coordinator（协调器）的LayoutParams初始化时会解析Behavior(行为)，该解析方法会反射该构造函数
     * @param context
     * @param attr
     */
    public BehaviorView(Context context, AttributeSet attr){
        super(context,attr);

        TypedArray typedArray = context.obtainStyledAttributes(attr, R.styleable.BehaviorView);
        mTargetId = getTargetId(typedArray);
        typedArray.recycle();
    }

    private int getTargetId(TypedArray typedArray){
        int indexCount = typedArray.getIndexCount();
        //找到该View的target属性
        for(int i=0;i<indexCount;i++){
            int attr = typedArray.getIndex(i);
            if(R.styleable.BehaviorView_target == attr){
                return typedArray.getResourceId(attr,0);
            }
        }
        return 0;
    }

    /**
     * 检查哪些view有依赖关系
     * @param parent
     * @param child 使用该Behavior的view
     * @param dependency child的依赖对象, 一般是触发Behavior的view
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, V child, View dependency) {
        //在这里判断dependency是不是自己要依赖的对象
//        return super.layoutDependsOn(parent, child, dependency);

        //使用当前Behavior的View和mTarget有依赖关系
        return dependency.getId() == mTargetId;
    }

    /*-----------------------依赖对象的大小或位置发生变化时使用下面方法--------------------------------*/
    /**
     * 依赖对象的大小或位置发生变化时会调用
     * 一般情况下child会根据dependency的大小或位置来调节自身的大小或位置
     * 比如不管dependency怎么变化，child一直在dependency的上面或下面。
     * @param parent
     * @param child
     * @param dependency
     * @return 如果child大小或位置发生变化返回true
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, V child, View dependency) {
        //这里根据dependency 设置child的大小或位置
        return super.onDependentViewChanged(parent, child, dependency);
    }

    /*--------------------依赖对象滑动时使用下面方法-------------------------*/

    /**
     * directTargetChild 滑动了，通知child。child判断下自己是否要处理滑动事件
     * coordinatorLayout下的target动了,检查下自己是不是要跟着滑动。
     * @param coordinatorLayout
     * @param child 使用该Behavior的view
     * @param directTargetChild 检查的对象
     * @param target  和directTargetChild一样
     * @param nestedScrollAxes
     * @return 自己也要跟着target动，就返回true
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V child, View directTargetChild, View target, int nestedScrollAxes) {
        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    /**
     * child跟着target滑动
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dxConsumed
     * @param dyConsumed
     * @param dxUnconsumed
     * @param dyUnconsumed
     */
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, V child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }
}
