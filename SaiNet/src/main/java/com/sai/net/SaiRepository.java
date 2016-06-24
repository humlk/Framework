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
import com.sai.net.request.RequestOptions;
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
        NetworkResponse networkResponse;
        if(cacheModel == RequestOptions.Model.NO_CACHE_REQUEST){
            if(entry != null){
                networkResponse = new NetworkResponse(entry.data, entry.responseHeaders);
                return request.parseNetworkResponse(networkResponse);
            }
        }else if(cacheModel == RequestOptions.Model.EXPIRED_CACHE_REQUEST){
            if(entry != null && !entry.isExpired()){
                //如果缓存没过期
                networkResponse = new NetworkResponse(entry.data, entry.responseHeaders);
                return request.parseNetworkResponse(networkResponse);
            }
        }

        request.setCacheEntry(entry);

        //request network
        networkResponse = mNetwork.performRequest(request);
        if(networkResponse == null && cacheModel == RequestOptions.Model.REQUEST_FAILED_READ_CACHE ){
            if(entry != null){
                networkResponse = new NetworkResponse(entry.data, entry.responseHeaders);
                return request.parseNetworkResponse(networkResponse);
            }
        }

        //解析networkResponse
        if(networkResponse == null){
            throw new SaiException("no response");
        }

        Response response = request.parseNetworkResponse(networkResponse);
        if(isShouldCache(cacheModel) && response.cacheEntry != null){
            mCache.put(request.getCacheKey(), response.cacheEntry);
        }
        return response;
    }


    private boolean isShouldCache(int cacheModel){
        if(cacheModel == RequestOptions.Model.NO_CACHE_REQUEST
                || cacheModel == RequestOptions.Model.REQUEST_FAILED_READ_CACHE
                || cacheModel == RequestOptions.Model.EXPIRED_CACHE_REQUEST){
            return true;
        }
        return false;
    }
}
