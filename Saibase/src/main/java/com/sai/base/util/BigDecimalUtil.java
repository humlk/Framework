package com.sai.base.util;

import java.math.BigDecimal;

/** 
 * 系统名称： </br>
 * 模块名称： </br>
 * 功能说明： </br>
 *  
 * @author: huajie 
 * @version: 1.0
 * @date:   2015年1月23日 
 *      
 */
public class BigDecimalUtil {

    private BigDecimalUtil(){};


    /**
     * 获取BigDecimal
     * @param num
     * @return
     */
    public static BigDecimal getBigDecimal(String num){
        if(StringUtil.isEmpty(num)){
            return BigDecimal.ZERO;
        }
        return new BigDecimal(num);
    }

    /**
     * 大于1,相等0,小于-1
     * @param num1
     * @param num2
     * @return
     */
    public static int compare(String num1, String num2){
        BigDecimal b1 = getBigDecimal(num1);
        BigDecimal b2 = getBigDecimal(num2);
        return b1.compareTo(b2);
    }
    
    /**
     * 如果是整形数据
     * @param num1
     * @param num2
     * @return
     */
    public static int compareInteger(String num1, String num2){
        Integer i1 = Integer.parseInt(num1);
        Integer i2 = Integer.parseInt(num2);
        
        return i1.compareTo(i2);
    }
}
