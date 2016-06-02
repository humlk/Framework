package com.sai.net.request.http;

import android.text.TextUtils;

import com.sai.base.util.FileUtil;
import com.sai.base.util.StreamUtil;
import com.sai.net.exception.SaiException;
import com.sai.net.http.HttpResponse;
import com.sai.net.http.NetworkResponse;
import com.sai.net.http.Response;
import com.sai.net.request.OnResponseListener;
import com.sai.net.request.RequestOptions;
import com.sai.net.request.RequestParams;
import com.sai.net.util.LogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.zip.GZIPInputStream;


public class FileRequest extends HttpRequest<Void>{

    private static final int BUFFER_SIZE = 200 * 1024; //20k
    private File tempFile;
    private File storeFile;

    public FileRequest(String url, String savePath, RequestParams requestParams, RequestOptions options, OnResponseListener<Void> listener) {
        super(url, requestParams, options, listener);
        tempFile = new File(savePath+".tmp~");
        storeFile = new File(savePath);
    }


    @Override
    public Response<byte[]> parseNetworkResponse(NetworkResponse response) {
        if (isCanceled()) {
            mRequestQueue.getDelivery().postCancel(this);
            return Response.error(new SaiException("请求被取消!"));
        }
        if (storeFile.exists()) {
            storeFile.delete();
        }
        if (tempFile.canRead() && tempFile.length() > 0 ) {
            if (tempFile.renameTo(storeFile)) {
                return Response.success(null, null);
            } else {
                return Response.error(new SaiException("文件命名失败!"));
            }
        } else {
            return Response.error(new SaiException("文件下载失败"));
        }
    }

    @Override
    protected void deliverResponse(byte[] response) {

        mResponseListener.onSuccess(null);
    }

    @Override
    public boolean handleResponse(HttpResponse httpResponse) {

        try {
            InputStream instream = httpResponse.getContentStream();
            long contentLength = httpResponse.getContentLength();

            //gzip处理
            if(isGzipContent(httpResponse) && !(instream instanceof GZIPInputStream)){
                instream = new GZIPInputStream(instream);
            }

            if(tempFile.exists()){
                tempFile.delete();
            }
            if (!tempFile.exists()) {
                FileUtil.makeDir(tempFile.getPath());
            }
            FileOutputStream fs = new FileOutputStream(tempFile);
            if (instream != null) {
                try {
                    byte[] buf = new byte[BUFFER_SIZE];
                    int size, count = 0;
                    while ((size = instream.read(buf)) != -1 && !isCanceled()) {
                        count += size;
                        fs.write(buf, 0, size);
                        mRequestQueue.getDelivery().postProgress(this, count, contentLength);
                        if (isCanceled()) {
                            mRequestQueue.getDelivery().postCancel(this);
                            break;
                        }
                    }
                    buf = null;
                } finally {
                    StreamUtil.close(instream);
                    StreamUtil.close(fs);
                }
            }
        } catch (IOException e) {
            LogUtil.d("文件操作失败,"+e.getMessage());
            mRequestQueue.getDelivery().postError(this, new SaiException(e.getMessage()));
        }
        return true;
    }

    public static boolean isGzipContent(HttpResponse response) {
        return TextUtils.equals(getHeader(response, "Content-Encoding"), "gzip");
    }

    private static String getHeader(HttpResponse httpResponse, String key){
        Map<String,String> header = httpResponse.getHeaders();
        if(header != null){
            return header.get(key);
        }
        return null;
    }
}
