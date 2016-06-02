package com.sai.demo.utils;

import android.util.Log;

/**
 * Created by huajie on 2016/6/1.
 */
public class LogUtils {
    private static String TAG = "app";
    public static void d(String message){
        d(TAG, message);
    }

    public static void d(String tag, String message){
        Log.d(tag, message);
    }
}
