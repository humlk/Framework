package com.sai.demo.utils;

import android.util.Log;

public class LogUtils {
    private static String TAG = "testLog";
    public static void d(String message){
        d(TAG, message);
    }

    public static void d(String tag, String message){
        Log.d(tag, message);
    }
}
