package com.sai.monitor.executor;

import android.view.View;

/**
 * 监控 View的绘制，层级
 * 使用lint命令检测
 */
public class ViewExecutor {

    private ViewExecutor() {

    }

    private static class Instance {
        static ViewExecutor ve = new ViewExecutor();
    }

    public ViewExecutor get() {
        return Instance.ve;
    }

    public int floor(View view){
        return 0;
    }
}
