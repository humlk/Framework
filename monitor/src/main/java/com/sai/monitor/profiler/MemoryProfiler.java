package com.sai.monitor.profiler;

import android.app.Application;

/**
 *
 */
public class MemoryProfiler {

    private Application mApplication;

    private static class Instance{
        static MemoryProfiler mp = new MemoryProfiler();
    }

    public static MemoryProfiler get(){
        return Instance.mp;
    }

    public static String getUsedSize(Application application){

        return null;
    }
}
