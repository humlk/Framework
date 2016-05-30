package com.sai.net.security;

import android.content.Context;

import com.sai.net.R;
import com.sai.net.util.LogUtil;

import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;


public class TrustSSLSocketFactory {

    public static SSLSocketFactory initSSL(Context context){
        try {
        
            InputStream in = context.getResources().openRawResource(R.raw.trustcert);
            KeyStore keystore = KeyStore.getInstance("BKS");
            keystore.load(in, "".toCharArray());
            in.close();

            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keystore);

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            return sslSocketFactory;
        } catch (Exception e) {
            LogUtil.show("ssl初始化失败：" + e.getMessage());
        }
        return null;
    }
}
