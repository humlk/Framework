package com.sai.base.util;

import android.util.Log;

/** 
 * 系统名称： </br>
 * 模块名称： </br>
 * 功能说明： </br>
 * 
 * @ClassName:  LoggerUtil  
 * @author: huajie 
 * @version: 1.0
 * @date:   2015年1月6日 
 *      
 */
public class BaseLogUtil {

    private static final String TAG = "applog";
    private static boolean debug = true;
    public static void debug(String msg){
//        System.out.println("....--> " +msg);
        if(debug && !StringUtil.isEmpty(msg)){
            Log.d(TAG, msg);
        }
    }
}
