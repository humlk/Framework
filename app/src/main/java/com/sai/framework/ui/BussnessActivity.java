package com.sai.framework.ui;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.sai.base.util.AppUtil;
import com.sai.base.util.BitmapUtil;
import com.sai.framework.MyApplication;
import com.sai.framework.R;
import com.sai.framework.model.ResponseBean;
import com.sai.net.SaiNet;
import com.sai.net.http.Request;
import com.sai.net.request.FileRequestBuilder;
import com.sai.net.request.RequestBuilder;
import com.sai.net.request.RequestCallBack;
import com.sai.net.request.RequestOptions;
import com.sai.net.request.RequestParams;
import com.sai.net.request.http.FileRequest;
import com.sai.net.request.http.HttpRequest;
import com.sai.net.util.LogUtil;

import org.json.JSONObject;

import butterknife.OnClick;


public class BussnessActivity extends BaseActivity {

    private final String TAG = "BussnessActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_buss);

    }


    @OnClick(R.id.btn_get)
    public void get() {

    }
    @OnClick(R.id.btn_get_restful)
    public void getRestful() {

        //没必要
        String url = new RequestBuilder.RestfulUrlBuilder().url("http://10.11.147.41:8004/app/projectsPage")
                .add("1.0.0").add(1).add(1).add(1).add(10).build();
        HttpRequest request = new RequestBuilder().url(url)
                .callBack(new RequestCallBack<ResponseBean>() {
                    @Override
                    public void onSuccess(ResponseBean response) {
                        super.onSuccess(response);
                        if(response != null){
                            LogUtil.d(response.toString());
                        }else{
                            LogUtil.d("返回数据为空");
                        }
                    }

                    @Override
                    public void onError(String code, String msg) {
                        super.onError(code, msg);
                        LogUtil.d("请求失败:"+msg);
                    }

                    @Override
                    public void onCancel() {
                        super.onCancel();
                        LogUtil.d("请求取消:");
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        LogUtil.d("请求结束");
                    }
                }).build();

        SaiNet.addRequest(request);
    }
    @OnClick(R.id.btn_post)
    public void post() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("key1", "1.0.0");

        RequestOptions options = new RequestOptions();
        options.method = Request.Method.POST;

        HttpRequest request = new RequestBuilder().url("").options(options)
                .callBack(new RequestCallBack<JSONObject>() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        super.onSuccess(response);
                    }

                    @Override
                    public void onError(String code, String msg) {
                        super.onError(code, msg);
                    }
                }).build();

        SaiNet.addRequest(request);
    }
    @OnClick(R.id.btn_upload)
    public void upload() {
        RequestParams requestParams = new RequestParams();

        Bitmap bitmap = BitmapUtil.getBitmap(MyApplication.get(), R.drawable.icon_test);
        if (bitmap == null) {
            LogUtil.d("图片资源为空");
            return;
        }
        requestParams.put("file", "test.jpg", BitmapUtil.bitmapTobytes(bitmap));
        requestParams.put("file", "test2.jpg", BitmapUtil.bitmapTobytes(bitmap));

        RequestOptions options = new RequestOptions();
        options.method = Request.Method.POST;

        HttpRequest request = new RequestBuilder().url("http://10.112.88.111:8085/fdfs_web/api/uploadFile4MultiPart").params(requestParams).options(options)
                .callBack(new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(String response) {
                        super.onSuccess(response);
                        LogUtil.d("上图图片结果：" + response);
                    }
                }).build();

        SaiNet.addRequest(request);
    }
    @OnClick(R.id.btn_download)
    public void download() {

        final String filePath = getStorePath()+"test.apk";
        LogUtil.d("文件存储地址："+ filePath);
        HttpRequest request = new FileRequestBuilder()
                .filePath(filePath)
                .url("http://10.112.88.105/group1/M00/00/00/CnBYbVc-e3CAXMHrAJS14RfPHaY386.apk")
                .callBack(new RequestCallBack<Void>() {
                    @Override
                    public void onSuccess(Void response) {
                        super.onSuccess(response);
                        LogUtil.d("apk下载成功");
                        AppUtil.installApk(MyApplication.get(), filePath);
                    }

                    @Override
                    public void onProgressChanged(long downloadSize, long fileSize) {
                        super.onProgressChanged(downloadSize, fileSize);
                        LogUtil.d("文件下载:" + downloadSize + ","+fileSize);

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        LogUtil.d("apk下载结束");
                    }
                }).build();

        SaiNet.addFileDownloadRequest((FileRequest)request);
    }

    private String getStorePath(){
        String path = AppUtil.getSDStore();
        return path +"/le/data/";
    }
}
