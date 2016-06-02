package com.sai.permission;

import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class PermissionManager {

    private static final String TAG = "PermissionManager";

    public static void request(Activity activity, int requestCode, String... permissions) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    public static boolean check(int[] grantResults) {
        for (int g : grantResults) {
            if (g == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

    public static boolean check(Application context, String[] permissions) {
        for (String p : permissions) {
            if (!has(context, p)) {
                //没有权限
                Log.d(TAG, "does not have the permission:" + p);
                context = null;
                return false;
            }
        }
        return true;
    }

    /**
     * 检查其他进程是否有权限
     * @param context
     * @param pid
     * @param uid
     * @param permissions
     * @return
     */
    public static boolean check(Application context, int pid, int uid, String[] permissions){
        for (String p : permissions) {
            if (!has(context, pid, uid, p)) {
                //没有权限
                Log.d(TAG, "does not have the permission:" + p);
                return false;
            }
        }
        return true;
    }

    /**
     * 检查其他包是否有权限
     * @param context
     * @param packageName
     * @param permissions
     * @return
     */
    public static boolean check(Application context, String packageName, String[] permissions){
        for (String p : permissions) {
            if (!has(context, packageName, p)) {
                //没有权限
                Log.d(TAG, "does not have the permission:" + p);
                return false;
            }
        }
        return true;
    }


    private static boolean has(Application context, int pid, int uid, String permission) {
        return PackageManager.PERMISSION_GRANTED == context.checkPermission(permission, pid, uid);
    }

    private static boolean has(Application context, String packageName, String permission) {
        PackageManager packageManager = context.getPackageManager();
        return PackageManager.PERMISSION_GRANTED == packageManager.checkPermission(permission, packageName);
    }

    private static boolean has(Application context, String permission) {
        //has permission
        return PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(context, permission);
    }
}
