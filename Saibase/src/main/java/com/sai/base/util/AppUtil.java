package com.sai.base.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.view.Window;

import java.io.File;
import java.util.List;

/**
 * 系统名称： </br>
 * 模块名称： </br>
 * 功能说明： 手机相关信息</br>
 *
 * @author: huajie-work
 * @version: 1.0
 * @date: 2015年3月2日
 */
public class AppUtil {

    private AppUtil() {
    }

    ;

    public static int versionCode(Context context) {
        int versionCode = -1;
        try {
            versionCode = context.getPackageManager().getPackageInfo(packageName(context), 0).versionCode;
        } catch (NameNotFoundException e) {
        }
        return versionCode;
    }

    public static String versionName(Context context) {
        String versionName = null;
        try {
            versionName = context.getPackageManager().getPackageInfo(packageName(context), 0).versionName;
        } catch (NameNotFoundException e) {
        }

        return versionName;
    }

    public static String packageName(Context context) {
        String pkName = context.getPackageName();
        return pkName;
    }

    public static void installApk(Context context, String path) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(path)), "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static String getSDStore() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return Environment.getExternalStorageDirectory().getPath();
        }
        return null;
    }

    public static String getStorePath(Application context){
        String path = getSDStore();
        if(StringUtil.isEmpty(path)){
            path = context.getFilesDir().getPath();
        }
        return path;
    }

    public static boolean isAppOnForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName()) && appProcess.importance ==
                    RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }

        return false;
    }


    public static void startApp(Context context) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        context.startActivity(intent);
    }


    public static void reStartApp(Context context) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);

        android.os.Process.killProcess(android.os.Process.myPid());    //获取PID
        System.exit(0);
    }


    public static boolean isAppRunning(Context context) {
        ActivityManager am = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));
        List<RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = context.getPackageName();
        for (RunningAppProcessInfo info : processInfos) {
            if (mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isServiceRunning(Context context, String serviceName) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceName.equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isActivityRunning(Context context, String activityName){
        ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
        boolean isAppRunning = false;
        for (ActivityManager.RunningTaskInfo info : list) {
            if (info.topActivity.getClassName().equals(activityName) || info.baseActivity.getClassName().equals(activityName)) {
                return true;
            }
        }
        return false;
    }
    public static boolean isMainProcess(Context context) {
        ActivityManager am = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));
        List<RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = context.getPackageName();
        int myPid = android.os.Process.myPid();
        for (RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

    public static View getRootView(Activity activity) {
        return activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT);
//        return ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
    }

}
