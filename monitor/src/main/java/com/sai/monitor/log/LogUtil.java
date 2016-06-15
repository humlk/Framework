package com.sai.monitor.log;


import android.text.TextUtils;
import android.util.Log;

public class LogUtil {
    private static boolean isDebug = true;
    private static String TAG = "monitor";

    public static void d(String tag, String msg) {
        if (isDebug && !TextUtils.isEmpty(msg)) {
            Log.d(tag, msg);
        }
    }

    public static void d(String msg) {
        d(TAG, msg);
    }
}
