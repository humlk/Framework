package com.sai.monitor.monitor;

import android.app.Application;

/**
 *
 */
public class MemoryMonitor {

    private Application mApplication;

    private static class Instance{
        static MemoryMonitor mp = new MemoryMonitor();
    }

    public static MemoryMonitor get(){
        return Instance.mp;
    }

    public static String getUsedSize(Application application){

        return null;
    }
}
