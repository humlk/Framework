package com.sai.monitor.log;


import android.text.TextUtils;
import android.util.Log;

public class MonitorLog {
    private static boolean isDebug = true;

    private static String TAG = "monitor";

    public static void log(String tag, String msg) {
        if (isDebug && !TextUtils.isEmpty(msg)) {
            Log.v(tag, msg);
        }
    }

    public static void log(String tag, String format, Object... msgs) {
        log(tag, String.format(format, msgs));
    }

    public static void log(String msg) {
        log(TAG, msg);
    }


    public static void d(String tag, String msg) {
        log(tag, msg);
    }

    public static void d(String msg) {
        log(TAG, msg);
    }


}
