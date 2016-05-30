package com.sai.net.request;

import com.sai.net.request.http.FileRequest;
import com.sai.net.request.http.HttpRequest;

public class FileRequestBuilder extends BaseRequestBuilder<Void>{
    //文件下载超时时间
    private static final int FILE_TIMEOUT = 300 * 1000;

    private String mFilePath;

    public FileRequestBuilder filePath(String path){
        mFilePath = path;
        return this;
    }

    @Override
    protected HttpRequest createRequest(String url) {
        mRequestOptions.timeOut = FILE_TIMEOUT;
        return new FileRequest(url,mFilePath,mRequestParams, mRequestOptions,mCallBackListener);
    }
}
