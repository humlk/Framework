package com.sai.demo.utils;

import android.util.Log;

public class LogUtils {
    private static String TAG = "testLog";

    public static void d(String message){
        d(TAG, message);
    }

    /**
     * 显示日志
     * @param tag
     * @param message
     */
    public static void d(String tag, String message){
        Log.d(tag, message);
    }
}
