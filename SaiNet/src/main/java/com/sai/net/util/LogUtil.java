package com.sai.net.util;

import android.util.Log;

import com.sai.base.util.StringUtil;


public class LogUtil {
    private static String TAG = "sai";

    private static boolean isDebug = true;

    public static void show(String tag, String msg){
        if(isDebug && !StringUtil.isEmpty(msg)){
            Log.d(tag,msg);
        }
    }

    public static void show(String msg){
        show(TAG,msg);
    }

    public static void e(String msg){
        if(isDebug && !StringUtil.isEmpty(msg)) {
            Log.e(TAG, msg);
        }
    }

    public static void d(String msg, Object ... s){
        if(isDebug && !StringUtil.isEmpty(msg)) {
            Log.d(TAG, String.format(msg,s));
        }
    }

    public static void setDebug(boolean isDebug) {
        LogUtil.isDebug = isDebug;
    }
}
