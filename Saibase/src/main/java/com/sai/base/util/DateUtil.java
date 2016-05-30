package com.sai.base.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 
 * 系统名称： </br>
 * 模块名称：时间操作 </br>
 * 功能说明： 时间操作的相关方法</br>
 * 
 * @ClassName:  DateUtil  
 * @author: huajie 
 * @version: 1.0
 * @date:   2014年12月29日 
 *      
 */
public class DateUtil {

    public static final String dateForamt = "yyyy-MM-dd HH:mm:ss";

    public static String millisFormat(int miss){
        String hh=miss/3600>9?miss/3600+"":"0"+miss/3600;
        String  mm=(miss % 3600)/60>9?(miss % 3600)/60+"":"0"+(miss % 3600)/60;
        String ss=(miss % 3600) % 60>9?(miss % 3600) % 60+"":"0"+(miss % 3600) % 60;
        return hh+":"+mm+":"+ss;
    }

    /**
     * 获取当前日期
     * currentTime   
     * @return: Date
     */
    public static Date currentTime() {
        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
        return calendar.getTime();
    }

    /**
     * 获取当前时间 
     * @param pattern （例 yyyy-MM-dd HH:mm:ss.SSSZ）</br>
     * hh表示用12小时制,  HH表示用24小时制
     * @return
     */
    public static String currentTime(String pattern) {

        return dateFormat(currentTime(), pattern);
    }
    
    public static String tomorrow(String pattern){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        
        return dateFormat(calendar.getTime(), pattern);
    }

    /**
     * 日期格式化 
     * @param date
     * @param pattern （例 yyyy-MM-dd HH:mm:ss.SSSZ）
     * @return
     */
    public static String dateFormat(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        DateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

//    public static String dateFormat(String strDate, String pattern) {
//        if (StringUtil.isEmpty(strDate)) {
//            return "";
//        }
//        DateFormat format = new SimpleDateFormat(pattern);
//
//        try {
//            return format.format(format.parse(strDate));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }

    public static Date dateFormat(String strDate, String pattern){
        if (StringUtil.isEmpty(strDate)) {
            return null;
        }
        DateFormat format = new SimpleDateFormat(pattern);

        try {
            return format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 时间比较 </br>
     * @param date1
     * @param date2
     * @return
     * date1 = date2 -> 0</br>
     * date1 < date2 -> -1</br>
     * date1 > date2 -> 1</br>
     * @throws ParseException 
     */
    public static int dateCompare(String date1,String pattern1, String date2,String pattern2) throws ParseException {
        DateFormat df1 = new SimpleDateFormat(pattern1);
        Date d1 = df1.parse(date1);
        DateFormat df2 = new SimpleDateFormat(pattern2);
        Date d2 = df2.parse(date2);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(d1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(d2);
        return calendar1.compareTo(calendar2);
    }

    /**
     * 比较两个日期的大小
     * @param date1
     * @param date2
     * @param pattern
     * @return 返回差值(单位天)
     * @throws ParseException 
     */
    public static int dateCompare(String date1, String date2, String pattern) throws ParseException {
        DateFormat df = new SimpleDateFormat(pattern);
        Date d1 = df.parse(date1);
        Date d2 = df.parse(date2);
        long result = d1.getTime() - d2.getTime();
        long days = result / (1000 * 60 * 60 * 24);
        return (int) days;
    }

    /**
     * 获取日期 yyyy-MM-dd
     * @param time yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getDate(String time){
        if(StringUtil.isEmpty(time)){
            return "";
        }
        int index = time.indexOf(" ");
        if(index != -1){
            return time.substring(0,index);
        }
        return time;
    }

    /**
     *  截取后部分时间
     * @param time yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getTime(String time){
        if(StringUtil.isEmpty(time)){
            return "";
        }
        int index = time.indexOf(" ");
        if(index != -1){
            return StringUtil.trim(time.substring(index));
        }
        return time;
    }

    /**
     * 检查是否是当日
     * @param data yyyy-MM-dd
     * @return data为null，则默认为当日
     */
    public static boolean isToday(String data){

        if(data == null || data.length() == 0){
            return false;
        }
        try {
            int result = DateUtil.dateCompare(data,DateUtil.currentTime("yyyy-MM-dd"),"yyyy-MM-dd");
            if(result == 0){
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }
}
