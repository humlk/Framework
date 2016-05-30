package com.sai.base.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

public class DeviceUtil {
    private static TelephonyManager tm;
    private static WifiManager wm;

    private static double screenSize;
    private static float screenDensity;
    private static int screenWidth;
    private static int screenHeight;

//    /**
//     * 呼叫号码.
//     *
//     * @param no
//     *            待呼叫的号码
//     */
//    public static void call(String no){
//        Uri uri = Uri.parse("tel:" + no);
//        context.startActivity(new Intent(Intent.ACTION_CALL, uri));
//    }

//    /**
//     * 发送邮件.
//     *
//     * @param mailto
//     *            收件人地址
//     * @param msg
//     *            邮件内容
//     */
//    public static void sendEMail(String mailto, String msg){
//
//    }
//
//    /**
//     * 发送短信.
//     *
//     * @param no
//     *            收信人号码
//     * @param msg
//     *            短信内容
//     */
//    public static void sendMessage(String no, String msg){
//        SmsManager smsManager = SmsManager.getDefault();
//        PendingIntent intent = PendingIntent.getBroadcast(
//                context,
//                0,
//                new Intent(),
//                PendingIntent.FLAG_ONE_SHOT);
//
//        smsManager.sendTextMessage(no, null, msg, intent, null);
//    }

    /**
     * 取得手机屏幕的密度
     *
     * @return 手机屏幕的密度
     */
    public static float getDensity(Context context) {
        float scale = context.getResources().getDisplayMetrics().density;
        return scale;
    }

    /**
     * 获取客户端屏幕宽度
     *
     * @return 客户端屏幕尺寸
     */
    public static int getScreenWidth(Context context) {
        initialization(context);
        return screenWidth;
    }

    /**
     * 获取客户端屏幕高度
     *
     * @return 客户端屏幕尺寸
     */
    public static int getScreenHeight(Context context) {
        initialization(context);
        return screenHeight;
    }

    /**
     * 电量栏高度
     *
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0;
        int sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return sbar;

//            int result = 0;
//            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
//            if (resourceId > 0) {
//                result = context.getResources().getDimensionPixelSize(resourceId);
//            }
    }

    /**
     * 获取客户端屏幕尺寸，单位英寸
     *
     * @return 客户端屏幕尺寸
     */
    public static double getScreenSize(Context context) {
        initialization(context);
        return screenSize;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param dip 转换前的dp值
     * @return 转换后的像素值
     */
    public static int dip2px(Context context, float dip) {
        initialization(context);
        return (int) (dip * screenDensity + 0.5f);
    }

    public static int px2Dip(Context context, float px) {
        initialization(context);
        return (int) (px / screenDensity + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param sp 转换前的sp值
     * @return 返回像素值
     */
    public static int sp2px(Context context, float sp) {
        initialization(context);
        return (int) (sp * screenDensity + 0.5f);
    }

    /**
     * 获取IMSI号
     *
     * @return IMSI号
     */
    public static String getIMSI(Context context) {
        initialization(context);
        String result = "";
        if (tm != null) {
            result = tm.getSubscriberId();
        }
        return result;
    }

    /**
     * 获取客户端类型
     *
     * @return 客户端类型，Android或aPad
     */
    public static String getDeviceType(Context context) {
        initialization(context);
        if (screenSize > 6) {
            //屏幕尺寸大于6寸的被识别为aPad
            return "aPad";
        } else {
            //否则识别为Android手机
            return "Android";
        }
    }

    /**
     * 获取IMEI
     *
     * @return IMEI号
     */
    public static String getIMEI(Context context) {
        initialization(context);
        String result = "";
        if (tm != null) {
            result = tm.getDeviceId();
        }
        return result;
    }

    /**
     * 获取ICCID
     *
     * @return ICCID号
     */
    public static String getICCID(Context context) {
        initialization(context);
        String result = "";
        if (tm != null) {
            result = tm.getSimSerialNumber();
        }
        return result;
    }

    /**
     * 获取手机系统版本
     *
     * @return 手机系统版本
     */
    public static String getPhoneOSVersion() {
        String osVersion = "";
        osVersion = Build.VERSION.RELEASE;
        osVersion = StringUtil.trim(osVersion);
        return osVersion;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号 eg:ME860 10 2.3.4
     */
    public static String getPhoneModel() {
        String deviceModel = "";
        //ME860            //10        API等级            //2.3.4 系统版本
        deviceModel = Build.MODEL;
        deviceModel = StringUtil.trim(deviceModel);
        return deviceModel;
    }

    /**
     * 获取手机产品代号
     *
     * @return 手机产品代号 eg:MOTO ME860_HKTW
     */
    public static String getPhoneProduct() {
        String phoneType = "";
        // eg：MOTO                    //eg:ME860_HKTW
        phoneType = Build.PRODUCT;
        phoneType = StringUtil.trim(phoneType);
        return phoneType;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商 eg:motorola
     */
    public static String getPhonePhoneManuFacturer() {
        String phoneManufactuer = "";
        phoneManufactuer = Build.MANUFACTURER;//eg:motorola
        phoneManufactuer = StringUtil.trim(phoneManufactuer);
        return phoneManufactuer;
    }

    /**
     * 获取手机网络运营商类型
     *
     * @return 手机网络运营商类型
     */
    public static String getPhoneISP(Context context) {
        String teleCompany = "";
        /*
         * MCC+MNC(mobile country code + mobile network code) 注意：仅当用户已在网络注册时有效。
         * 在CDMA网络中结果也许不可靠。
         */
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String np = tm.getNetworkOperator();// String
        if (np != null) {
            if (np.startsWith("46000") || np.startsWith("46002")) {// 因为移动网络编号46000下的IMSI已经用完，所以虚拟了一个46002编号，134/159号段使用了此编号
                // 中国移动
                teleCompany = "y";
            } else if (np.startsWith("46001")) {
                // 中国联通
                teleCompany = "l";
            } else if (np.startsWith("46003")) {
                // 中国电信
                teleCompany = "d";
            }
        }
        teleCompany = StringUtil.trim(teleCompany);
        return teleCompany;
    }

    /**
     * 获取mac地址
     *
     * @return mac地址
     */
    public static String getMac(Context context) {
        initialization(context);
        if (wm != null && wm.getConnectionInfo() != null) {
            WifiInfo wifiInfo = wm.getConnectionInfo();
            if (wifiInfo != null) {

                return wifiInfo.getMacAddress();
            }
        }
        return null;
    }

    /**
     * 获取ip地址
     *
     * @return ip地址
     */
    public static String getIpAddress(Context context) {
        initialization(context);
        if (wm != null && wm.getConnectionInfo() != null) {
            WifiInfo wifiInfo = wm.getConnectionInfo();
            if (wifiInfo != null) {
                return intToIp(wifiInfo.getIpAddress());
            }
        }
        return null;
    }


    private static String intToIp(int ip) {

        StringBuffer sb = new StringBuffer();
        sb.append(ip & 0xFF).append(".");
        sb.append((ip >> 8) & 0xFF).append(".");
        sb.append((ip >> 16) & 0xFF).append(".");
        sb.append((ip >> 24) & 0xFF).append(".");
        return sb.toString();
    }

    /**
     * 获取存储路径
     *
     * @return 存储路径
     */
    public static String getContentStoragePath(Context context) {
        String path = "";
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            path = Environment.getExternalStorageDirectory().getPath();
        } else {
            path = context.getFilesDir().getPath();
        }
        return path + "/";
    }

//    /**
//     * 获取浏览器内核类型
//     * @return 浏览器内核类型
//     */
//    public static String getBrowserCoreType(){
//        return browserCoreType;
//    }
//
//    /**
//     * 获取浏览器内核版本号
//     * @return 浏览器内核版本号
//     */
//    public static String getBrowserCoreVersion(){
//        return browserCoreVersion;
//    }

    /**
     * 检查照相机是否可用
     *
     * @return 可用返回true，否则返回false
     */
    @SuppressLint("NewApi")
    public static boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 网络是否可用
     *
     * @return true可用 </br> false不可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 返回当前手机接入的网络类型,
     *
     * @return 返回值: 1.代表mobile(2G3G), 2代表wifi
     */
    public static int getNetworkType(Context context) {
        int ret = 0;
        try {
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
            if (activeInfo != null && activeInfo.isConnected()) {

                if (activeInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    ret = 2;
                } else if (activeInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    ret = 1;
                }
            }
        } catch (Exception e) {
        }
        return ret;
    }

//    /**
//     * 保存照片到相册
//     *
//     * @param bitmap
//     * @param filename
//     * @return 成功返回true，否则返回false
//     */
//    //TODO 移到合适的类中
//    public static boolean saveBitmapToPhoto(Bitmap bitmap, String filename) {
//        ContentResolver cr = context.getContentResolver();
//        String result = MediaStore.Images.Media.insertImage(cr, bitmap,filename, "");
//
//        return (result != null) ? true : false;
//    }

    /**
     * 工具类初始化
     */
    public static void initialization(Context context) {
        if (tm != null) {
            return;
        }
        tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        //计算客户端屏幕尺寸
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        screenSize = Math.sqrt(dm.widthPixels * dm.widthPixels + dm.heightPixels * dm.heightPixels) / (dm.density * 160);

        screenDensity = dm.density;
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
//        
//        String ua = new WebView(context).getSettings().getUserAgentString();
//
//        browserCoreType = "AppleWebKit";
//        int start = ua.indexOf(browserCoreType);
//        if(start==-1){
//            browserCoreType = "Unknown";
//        }else {
//            start += browserCoreType.length();
//            int end = ua.indexOf(" ",start);
//            if(end==-1){
//                end = ua.length();
//            }
//            browserCoreVersion = ua.substring(start, end);
//            if (browserCoreVersion.startsWith("/")) {
//                browserCoreVersion=browserCoreVersion.substring(1);
//            }
//        }
    }
}
