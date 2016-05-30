package com.sai.permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class PermissionManager {

    private static final String TAG = "PermissionManager";

    public static void request(Activity activity, int requestCode, String... permissions){
        ActivityCompat.requestPermissions(activity,permissions,requestCode);
    }

    public static boolean check(int[] grantResults){
        for(int g:grantResults){
            if(g == PackageManager.PERMISSION_DENIED){
                return false;
            }
        }
        return true;
    }
    public static boolean check(Context context, String... permissions){
        for(String p: permissions){
            if(!hasPermission(context, p)){
                //没有权限
                Log.d(TAG, "does not have the permission:" + p);
                context = null;
                return false;
            }
        }
        context = null;
        return true;
    }

    private static boolean hasPermission(Context context, String permission){
       int result =  ContextCompat.checkSelfPermission(context,permission);

        //have permission
        return result == PackageManager.PERMISSION_GRANTED;
    }
}
