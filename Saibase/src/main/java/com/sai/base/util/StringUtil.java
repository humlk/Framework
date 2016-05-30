package com.sai.base.util;

import java.math.BigDecimal;
import java.util.Random;
import java.util.regex.Pattern;

/** 
 * 系统名称： </br>
 * 模块名称： </br>
 * 功能说明： </br>
 * 
 * @ClassName:  StringUtil  
 * @author: huajie 
 * @version: 1.0
 * @date:   2014年12月26日 
 *      
 */
public class StringUtil {

    private StringUtil() {
    }

    /**
     * 检查字符串是否为空
     * isEmpty   
     * @param string
     * @return: boolean
     */
    public static boolean isEmpty(String string) {
        if (string != null && string.length() > 0) {
            return false;
        }
        return true;
    }

    public static boolean isEmpty(Object object) {
        if (object != null && String.valueOf(object).length() > 0) {
            return false;
        }
        return true;
    }

    /**
     * 是否包含空对象
     * hasEmpty   
     * @param strings
     * @return: boolean
     */
    public static boolean hasEmpty(String... strings) {

        for (String string : strings) {
            if (isEmpty(string)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 全部为空
     * @param strings
     * @return
     */
    public static boolean allEmpty(String... strings) {

        for (String string : strings) {
            if (!isEmpty(string)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 如果为null, 返回空字符串
     * @param string
     * @return      
     * @return: String
     */
    public static String trim(String string) {
        if (isEmpty(string)) {
            return "";
        }
        return string.trim();
    }

    /**
     * 如果为null，则返回defValue
     * @param string
     * @param defValue
     * @return
     */
    public static String trim(String string, String defValue) {
        if (isEmpty(string)) {
            return defValue;
        }
        return string.trim();
    }

    /**
     * 获取随机字符串
     * random   
     * @param length
     * @return      
     * @return: String
     */
    public static String randomString(int length) {

        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(base.length());
            sb.append(base.charAt(index));
        }

        return sb.toString();
    }

    /**
     * 数字随机数
     * @param length
     * @return
     */
    public static String random(int length) {
        StringBuffer val = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            val.append(random.nextInt(10));
        }
        return val.toString();
    }

    /**
     * 判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (isEmpty(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]{1,})?$");
        return pattern.matcher(str).matches();
    }

    /**
     * 金额分转元
     * @param amount
     * @return
     */
    public static String CentToYuan(String amount) {
        if (isEmpty(amount)) {
            return "0";
        }
        BigDecimal a = new BigDecimal(amount);
        return a.divide(new BigDecimal(100)).toString();
    }

    public static String YuanToCent(String s) {
        s = trim(s);
        int i = s.indexOf(".");
        if (i == -1)
            return s + "00";
        String intStr = s.substring(0, i);
        if (intStr.length() <= 0)
            intStr = "0";
        String decStr = s.substring(i + 1, s.length());
        if (decStr.length() <= 0)
            decStr = "00";
        if (decStr.length() == 1)
            decStr += "0";
        if (decStr.length() > 2)
            decStr = decStr.substring(0, 2);
        int iInt = Integer.parseInt(intStr);
        if (iInt <= 0)
            return decStr;
        else
            return intStr + decStr;
    }

    /**
     * 简体中文
     * @param string
     * @return
     */
    public static boolean isSimplifiedChinese(String string){
        String regExp = "^[\u4E00-\u9FA5]+$";    
        Pattern p = Pattern.compile(regExp);   
        return p.matcher(string).matches();
    }

    /**
     * 是否是中文汉字
     * @param string
     * @return
     */
    public static boolean isChinese(String string){
        String regExp = "^[\u4E00-\u9FFF]+$";    
        Pattern p = Pattern.compile(regExp);   
        return p.matcher(string).matches();
    }

    /**
     * 前面补足
     *
     * @param string
     * @param length
     * @param fill
     * @return
     */
    public static String prefixfill(String string, int length, String fill) {
        if (StringUtil.isEmpty(string)) {
            string = "";
        }
        if (string.length() > length) {
            return string;
        }
        int fillLength = length - string.length();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < fillLength; i++) {
            buffer.append(fill);
        }
        buffer.append(string);
        return buffer.toString();
    }

    /**
     * 后面补足
     *
     * @param string
     * @param length
     * @param fill
     * @return
     */
    public static String suffixfill(String string, int length, String fill) {
        if (StringUtil.isEmpty(string)) {
            string = "";
        }
        if (string.length() > length) {
            return string;
        }
        int fillLength = length - string.length();
        StringBuffer buffer = new StringBuffer(string);
        for (int i = 0; i < fillLength; i++) {
            buffer.append(fill);
        }
        return buffer.toString();
    }

    /**
     * 使用StringBuilder 生成String
     * @param msgs
     * @return
     */
    public static String toString(String ... msgs){

        StringBuilder builder = new StringBuilder();
        for(String msg:msgs){
            if(!StringUtil.isEmpty(msg)){
                builder.append(msg);
            }
        }
        return builder.toString();
    }
}
