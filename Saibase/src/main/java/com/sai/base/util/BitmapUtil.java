package com.sai.base.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/** 
 * 系统名称： </br>
 * 模块名称： </br>
 * 功能说明： </br>
 * 
 * @ClassName:  BitmapUtil  
 * @author: huajie 
 * @version: 1.0
 * @date:   2014年12月30日 
 *      
 */
public class BitmapUtil {

    private BitmapUtil(){}

    public static Bitmap getBitmap(Context context, int resId){
        Options options = new Options();
        options.inJustDecodeBounds = false;
        InputStream in = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(in, null, options);
    }

    public static Bitmap getBitmap(Context context, int resId, int maxWidth, int maxHeight){
        Options options = new Options();
        options.inJustDecodeBounds = true;
        InputStream in = context.getResources().openRawResource(resId);
        BitmapFactory.decodeStream(in, null, options);
        setOption(options, maxWidth, maxHeight);
        
        return BitmapFactory.decodeStream(in, null, options);
    }
    
    
    private static void setOption(Options options, int maxWidth, int maxHeight){
        
        int size = 1;
        if(maxWidth != 0 && maxHeight != 0){
            int w_s = options.outWidth / maxWidth;
            w_s = options.outWidth % maxWidth == 0? w_s:w_s+1;
            
            int h_s = options.outHeight / maxHeight;
            h_s = options.outHeight % maxHeight == 0? h_s:h_s+1;
            
            size = w_s >= h_s?w_s:h_s;
        }
//        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inJustDecodeBounds = true;
        options.inSampleSize = size;
    }

    /**
     *
     * @param mBitmap
     * @return
     */
    public static byte[] bitmapTobytes(Bitmap mBitmap){
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
        return os.toByteArray();
    }
    

    
    /**
     * 横向平铺
     * @param width
     * @param src
     * @return
     */
    public static Bitmap createRepeater(int width, Bitmap src){
        int count = (width + src.getWidth() -1)/src.getWidth();
        Bitmap bitmap = Bitmap.createBitmap(width, src.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        for(int idx=0;idx<count;idx++){
            canvas.drawBitmap(src, idx * src.getWidth(), 0, null);
        }
        return bitmap;
    }
    
    /**
     * 释放Imageview
     * @param view
     */
    @Deprecated
    public static void imageviewRelease(ImageView view){
        if(view == null){
            return;
        }
//        setBackgroundResource和android:background →setBackgroundResource(0);
//        setBackgroundDrawable(background)→ setBackgroundDrawable(null)
        
        view.setImageResource(0);
        view.setImageDrawable(null);
        view.setImageBitmap(null);

    }

    public static Bitmap resizeImage(Bitmap bitmap, int w, int h)
    {
        Bitmap BitmapOrg = bitmap;
        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        int newWidth = w;
        int newHeight = h;

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width,
                height, matrix, true);
        return resizedBitmap;
    }


}
