package com.sai.demo.data.remote;


import com.sai.framework.data.callback.BaseLoadCallBack;
import com.sai.framework.data.remote.NetRequestLogic;
import com.sai.net.request.RequestBuilder;
import com.sai.net.request.http.HttpRequest;

public class DemoLogic extends NetRequestLogic {

    public void requestPage(final BaseLoadCallBack callBack) {
        String url = new RequestBuilder.RestfulUrlBuilder().url("app/projectsPage")
                .add("1.0.0").add(1).add(1).add(1).add(10).build();
        HttpRequest request = new RequestBuilder().url(url).tag(getTag())
                .callBack(callBack).build();

        startRequest(request);
    }

//    @Override
//    protected Object getTag() {
//        return "demoLogic";
//    }
}
