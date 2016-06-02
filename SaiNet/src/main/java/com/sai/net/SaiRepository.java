package com.sai.net;

import com.sai.net.cache.Cache;
import com.sai.net.cache.DiskBasedCache;
import com.sai.net.exception.SaiException;
import com.sai.net.http.BasicNetwork;
import com.sai.net.http.Network;
import com.sai.net.http.NetworkResponse;
import com.sai.net.http.OkHttpStack;
import com.sai.net.http.Request;
import com.sai.net.http.Response;
import com.sai.net.security.TrustSSLSocketFactory;

import java.io.File;

import javax.net.ssl.SSLSocketFactory;

import okhttp3.OkHttpClient;


public class SaiRepository {

    private static final String DEFAULT_CACHE_DIR = "sai";

    private Network mNetwork;
    private Cache mCache;
    private static SaiRepository instance = null;

    private SaiRepository() {

        initHttpClient();

        initCache();
    }

    private void initCache() {
        File cacheDir = new File(SaiNet.getContext().getCacheDir(), DEFAULT_CACHE_DIR);
        mCache = new DiskBasedCache(cacheDir);
    }

    private void initHttpClient() {
        OkHttpStack stack = getDefaultHttpStack();
        mNetwork = new BasicNetwork(stack);
    }

    private OkHttpStack getDefaultHttpStack() {
        SSLSocketFactory sslSocketFactory = TrustSSLSocketFactory.initSSL(SaiNet.getContext());
        OkHttpClient client = new OkHttpClient.Builder().sslSocketFactory(sslSocketFactory).build();
        return new OkHttpStack(client, sslSocketFactory);
    }

    public static SaiRepository get() {
        if (instance == null) {
            synchronized (SaiRepository.class) {
                if (instance == null) {
                    instance = new SaiRepository();
                }
            }
        }
        return instance;
    }

    public Cache getCache(){
        return mCache;
    }

    public Network getNetwork(){
        return mNetwork;
    }

    public Response getResponse(Request request) throws SaiException {
        //先检查cache
        String cacheKey = request.getCacheKey();
        int cacheModel = request.getCacheModel();
        Cache.Entry entry = mCache.get(cacheKey);
        NetworkResponse networkResponse = null;
        if(cacheModel == Cache.Model.ONLY_READ_CACHE){
            if(entry != null){
                networkResponse = new NetworkResponse(entry.data, entry.responseHeaders);
            }else{
                networkResponse = new NetworkResponse(null, null);
            }
        }else if(cacheModel == Cache.Model.NO_CACHE_REQUEST){
            if(entry != null){
                networkResponse = new NetworkResponse(entry.data, entry.responseHeaders);
            }
        }

        //request network
        networkResponse = mNetwork.performRequest(request);
        if(networkResponse == null && cacheModel == Cache.Model.REQUEST_FAILED_READ_CACHE ){
            if(entry != null){
                networkResponse = new NetworkResponse(entry.data, entry.responseHeaders);
            }
        }

        //解析networkResponse
        if(networkResponse != null){
            return request.parseNetworkResponse(networkResponse);
        }
        throw new SaiException("no response");
    }
}
