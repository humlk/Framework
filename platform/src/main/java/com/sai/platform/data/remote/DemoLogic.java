package com.sai.platform.data.remote;


import com.sai.net.request.RequestBuilder;
import com.sai.net.request.http.HttpRequest;
import com.sai.platform.data.callback.BaseLoadCallBack;

public class DemoLogic extends NetRequestLogic {

    public void requestPage(int startPage, final BaseLoadCallBack callBack) {
        String url = new RequestBuilder.RestfulUrlBuilder().url("http://10.11.147.41:8004/app/projectsPage")
                .add("1.0.0").add(1).add(1).add(1).add(10).build();
        HttpRequest request = new RequestBuilder().url(url).tag(getTag())
                .callBack(callBack).build();

        startRequest(request);
    }

    @Override
    protected Object getTag() {
        return "demoLogic";
    }
}
