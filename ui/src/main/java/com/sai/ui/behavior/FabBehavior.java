package com.sai.ui.behavior;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class FabBehavior extends BehaviorView<View>{

    /**
     * 该方法是必须的
     * Coordinator（协调器）的LayoutParams初始化时会解析Behavior(行为)，
     * @param context
     * @param attr
     */
    public FabBehavior(Context context, AttributeSet attr){
        super(context,attr);
    }

}
