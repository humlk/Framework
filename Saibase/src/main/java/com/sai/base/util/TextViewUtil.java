package com.sai.base.util;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;

/** 
 * 系统名称： </br>
 * 模块名称： </br>
 * 功能说明： </br>
 *  
 * @author: huajie 
 * @version: 1.0
 * @date:   2015年1月16日 
 *      
 */
public class TextViewUtil {

    private TextViewUtil(){};
    /**
     * 设置字体大小
     * @param str
     * @param start
     * @param end
     * @param size
     * @return
     */
    public static SpannableString setSize( String str, int start, int end, int size){
        SpannableString span = new SpannableString(str);
        span.setSpan(new AbsoluteSizeSpan(size), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        
        return span;
    }
    
    /**
     * 添加下划线
     * @param str
     * @param start
     * @param end
     * @return
     */
    public static SpannableString addUnderLine( String str, int start, int end){
        SpannableString span = new SpannableString(str);
        span.setSpan(new UnderlineSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        
        return span;
    }
    
    /**
     * 添加删除线
     * @param str
     * @param start
     * @param end
     * @return
     */
    public static SpannableString addDeleteLine( String str, int start, int end){
        SpannableString span = new SpannableString(str);
        span.setSpan(new StrikethroughSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        
        return span;
    }
    
    /**
     * 设置文字背景色
     * @param str
     * @param start
     * @param end
     * @param color 如Color.RED
     * @return
     */
    public static SpannableString setBackGroundColor( String str, int start, int end, int color){
        SpannableString span = new SpannableString(str);
        span.setSpan(new BackgroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        
        return span;
    }
    
    /**
     * 设置前景色
     * @param str
     * @param start
     * @param end
     * @param color
     * @return
     */
    public static SpannableString setForegGroundColor( String str, int start, int end, int color){
        SpannableString span = new SpannableString(str);
        span.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        
        return span;
    }
    
    /**
     * 设置前景色
     * @param str
     * @param start
     * @param end
     * @param color 如 #FF0000
     * @return
     */
    public static SpannableString setForegGroundColor(String str, int start, int end, String color){
        SpannableString span = new SpannableString(str);
        span.setSpan(new ForegroundColorSpan(Color.parseColor(color)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        
        return span;
    }
    
    /**
     * 设置粗体
     * @param str
     * @param start
     * @param end
     * @param type 如 Typeface.BOLD(粗体) Typeface.ITALIC(斜体) Typeface.BOLD_ITALIC(粗斜体)
     * @return
     */
    public static SpannableString setTextType(String str, int start, int end, int type){
        SpannableString span = new SpannableString(str);
        span.setSpan(new StyleSpan(type), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        
        return span;
    }
    
}
