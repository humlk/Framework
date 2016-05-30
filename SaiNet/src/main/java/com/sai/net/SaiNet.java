package com.sai.net;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.sai.base.util.DeviceUtil;
import com.sai.net.common.RequestError;
import com.sai.net.request.http.FileRequest;
import com.sai.net.request.http.HttpRequest;
import com.sai.net.http.Request;

public class SaiNet implements SaiConstant {

    private static Application mApplication = null;

    private static int currentNetworkState;

    private static BroadcastReceiver networkBroadcastReceiver;

    private static SaiHelper mConfig = new SaiHelper();


    private SaiNet() {
    }

    public static void init(Application application) {
        mApplication = application;

        registerNetwork();
    }

    private static void registerNetwork(){
        currentNetworkState = DeviceUtil.getNetworkType(mApplication.getApplicationContext());
        if (networkBroadcastReceiver == null) {
            networkBroadcastReceiver = new BroadcastReceiver() {

                @Override
                public void onReceive(Context context, Intent intent) {
                    int newNetworkState = DeviceUtil.getNetworkType(mApplication.getApplicationContext());
                    if (newNetworkState != currentNetworkState) {
                        currentNetworkState = newNetworkState;
                    }
                }
            };

            IntentFilter mFilter = new IntentFilter();
            mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            mApplication.getApplicationContext().registerReceiver(networkBroadcastReceiver, mFilter);
        }
    }

    public static SaiHelper getConfig(){
        return mConfig;
    }

    public static Context getContext() {
        return mApplication.getApplicationContext();
    }


    public static int getCurrentNetworkState(){
        return currentNetworkState;
    }


    public  static <T> void addRequest(HttpRequest<T> request){
        if (SaiNet.getCurrentNetworkState() == SaiNet.NetState.NULL) {
            request.deliverError(RequestError.CODE_NET_NULL, RequestError.MSG_NET_NULL);
            request.deliverFinish();
        }else{
            SaiVolley.get().getRequestQueue().add(request);
        }
    }

    public static void addFileDownloadRequest(FileRequest request){
        if (SaiNet.getCurrentNetworkState() == SaiNet.NetState.NULL) {
            request.deliverError(RequestError.CODE_NET_NULL, RequestError.MSG_NET_NULL);
            request.deliverFinish();
        }else{
            SaiVolley.get().getFileDownloadQueue().add(request);
        }
    }

    public static void cancelRequest(Request request){
        SaiVolley.get().getRequestQueue().cancelAll(request.getTag());
    }

    public static void cancelAllRequest(Object tag){
        SaiVolley.get().getRequestQueue().cancelAll(tag);
    }
}
