package com.sai.net.response;

import com.sai.net.util.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonResponse extends ResponseParse {

    @Override
    public JSONObject parse(byte[] data) {
        String s = null;
        try {
            s = new String(data);
            return new JSONObject(s);
        } catch (JSONException e) {
            LogUtil.d("json数据解析失败:"+s);
            return null;
        }
    }
}
