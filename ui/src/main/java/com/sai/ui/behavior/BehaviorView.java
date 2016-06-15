package com.sai.ui.behavior;


import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

public class BehaviorView <V extends View> extends CoordinatorLayout.Behavior<V>{
    /**
     * 该方法是必须的
     * Coordinator（协调器）的LayoutParams初始化时会解析Behavior(行为)，该解析方法回反射该构造函数
     * @param context
     * @param attr
     */
    public BehaviorView(Context context, AttributeSet attr){
        super(context,attr);
    }

    /**
     * 检查哪些view由依赖关系
     * @param parent
     * @param child child
     * @param dependency child的依赖对象
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, V child, View dependency) {
        return super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, V child, View dependency) {
        return super.onDependentViewChanged(parent, child, dependency);
    }
}
