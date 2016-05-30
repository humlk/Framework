package com.sai.base.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class SharedPreferencesUtil {

    public static void put(Context context, String key, String value){
        SharedPreferences sp = context.getSharedPreferences("judge_sp", Activity.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }
    
    public static String getString(Context context, String key, String defValue){
        SharedPreferences sp = context.getSharedPreferences("judge_sp", Activity.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }
}
