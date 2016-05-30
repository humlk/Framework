package com.sai.base.util;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;


import java.io.BufferedReader;
import java.io.InputStreamReader;


public class ResourceUtil {

    private ResourceUtil() {
    }

    /**
     * 根据资源名称返回资源在R文件中的ID
     *
     * @param name 资源名
     * @param type 资源分类
     * @return ID
     */
    public static int getResourceIdByName(Context context, String name, String type) {
        int resId = 0;
        try {
            resId = context.getResources().getIdentifier(name, type, context.getPackageName());
        } catch (Exception e) {
        }
        return resId;
    }

    /**
     * 根据资源名称返回图片对象
     *
     * @param name 资源名
     * @return 图片对象
     */
    public static Drawable getDrawable(Context context, String name) {
        return context.getResources().getDrawable(getDrawableId(context, name));
    }

    public static int getDrawableId(Context context, String name) {
        return getResourceIdByName(context, name, "drawable");
    }

    /**
     * 根据资源名称返回动画
     *
     * @param name 资源名
     * @return 图片对象
     */
    public static XmlResourceParser getAnimation(Context context, String name) {
        return context.getResources().getAnimation(getResourceIdByName(context, name, "anim"));
    }

    /**
     * 根据资源名称返回布尔值
     *
     * @param name 资源名
     * @return 图片对象
     */
    public static boolean getBoolean(Context context, String name) {
        return context.getResources().getBoolean(getResourceIdByName(context, name, "bool"));
    }

    /**
     * 根据资源名称返回颜色值
     *
     * @param name 资源名
     * @return 图片对象
     */
    public static int getColor(Context context, String name) {
        return context.getResources().getColor(getResourceIdByName(context, name, "color"));
    }

    public static int getColor(Context context, int resId) {
        return context.getResources().getColor(resId);
    }

    /**
     * 根据资源名称返回长度值
     *
     * @param name 资源名
     * @return 图片对象
     */
    public static float getDimension(Context context, String name) {
        return context.getResources().getDimension(getResourceIdByName(context, name, "dimen"));
    }

    /**
     * 根据资源名称返回整型数组
     *
     * @param name 资源名
     * @return 图片对象
     */
    public static int[] getIntArray(Context context, String name) {
        return context.getResources().getIntArray(getResourceIdByName(context, name, "array"));
    }

    /**
     * 根据资源名称返回整数
     *
     * @param name 资源名
     * @return 图片对象
     */
    public static int getInteger(Context context, String name) {
        return context.getResources().getInteger(getResourceIdByName(context, name, "integer"));
    }

    /**
     * 根据资源名称返回布局
     *
     * @param name 资源名
     * @return 图片对象
     */
    public static XmlResourceParser getLayout(Context context, String name) {
        return context.getResources().getLayout(getResourceIdByName(context, name, "layout"));
    }

    /**
     * 根据资源名称返回动画
     *
     * @param name 资源名
     * @return 图片对象
     */
    public static Movie getMovie(Context context, String name) {
        return context.getResources().getMovie(getResourceIdByName(context, name, "movie"));
    }

    public static String getString(Context context, int strId) {
        return context.getResources().getString(strId);
    }

    /**
     * 根据资源名称返回字符串
     *
     * @param name 资源名
     * @return 图片对象
     */
    public static String getString(Context context,String name) {
        return getString(context, name, null);
    }

    /**
     * 根据资源名称返回字符串
     *
     * @param name 资源名
     * @param def  默认值
     * @return 图片对象
     */
    public static String getString(Context context, String name, String def) {
        return context.getResources().getString(getResourceIdByName(context, name, "string"), def);
    }

    /**
     * 根据资源名称返回字符数组
     *
     * @param name 资源名
     * @return 图片对象
     */
    public static String[] getStringArray(Context context, String name) {
        return context.getResources().getStringArray(getResourceIdByName(context, name, "array"));
    }

    /**
     * 根据资源名称返回字符串
     *
     * @param name 资源名
     * @return 图片对象
     */
    public static CharSequence getText(Context context, String name) {
        return getText(context, name, null);
    }

    /**
     * 根据资源名称返回字符串
     *
     * @param name 资源名
     * @param def  默认值
     * @return 图片对象
     */
    public static CharSequence getText(Context context, String name, String def) {
        return context.getResources().getText(getResourceIdByName(context, name, "string"), def);
    }

    /**
     * 根据资源名称返回xml
     *
     * @param name 资源名
     * @return 图片对象
     */
    public static XmlResourceParser getXml(Context context, String name) {
        return context.getResources().getXml(getResourceIdByName(context, name, "xml"));
    }

    /**
     * 获取raw下文件
     *
     * @param fileName
     * @return
     */
    public static String getFromRaw(Context context, String fileName) {
        String res = "";
        int rawId = getResourceIdByName(context, fileName, "raw");
        if (rawId == 0) {
            return null;
        }

        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;
        try {
            inputReader = new InputStreamReader(context.getResources().openRawResource(rawId));
            bufReader = new BufferedReader(inputReader);
            String line = "";
            StringBuffer buffer = new StringBuffer();
            while ((line = bufReader.readLine()) != null)
                buffer.append(line);
            return buffer.toString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputReader != null) {
                    inputReader.close();
                }
                if (bufReader != null) {
                    bufReader.close();
                }
            } catch (Exception e2) {

            }

        }

        return res;
    }

    /**
     * 获取assert文件
     *
     * @param fileName
     * @return
     */
    public static String getFromAssets(Context context,String fileName) {
        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;

        try {
            inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            bufReader = new BufferedReader(inputReader);
            String line = "";
            StringBuffer buffer = new StringBuffer();
            while ((line = bufReader.readLine()) != null)
                buffer.append(line);
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputReader != null) {
                    inputReader.close();
                }
                if (bufReader != null) {
                    bufReader.close();
                }
            } catch (Exception e2) {

            }
        }
        return null;
    }
}
