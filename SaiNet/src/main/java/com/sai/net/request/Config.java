package com.sai.net.request;

import com.sai.net.SaiNet;
import com.sai.net.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

 class Config {

    public static final String URL_BASE;

    public static final String URL_IMAGE_BASE;
    public static final String URL_RES_BASE;

    public static final String DATA_PATH;

    private static Properties mProperties = null;


    static{
        mProperties = new Properties();
        InputStream is = SaiNet.getContext().getResources().openRawResource(R.raw.http_config);
        try {
            mProperties.load(is);

        } catch (IOException e) {
        }finally{
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        URL_BASE = mProperties.getProperty("server");
        URL_IMAGE_BASE = mProperties.getProperty("img_server");
        URL_RES_BASE = mProperties.getProperty("res_server");

        DATA_PATH = SaiNet.getContext().getFilesDir() + "/DATA/";
    }

     public static String fillUrl(String url) {
         if (!url.startsWith("http://") && !url.startsWith("https://")) {
             url = String.format("%s%s", Config.URL_BASE, url);
         }
         return url;
     }
}
