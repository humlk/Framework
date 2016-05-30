package com.sai.net.response;

import com.sai.base.util.JsonObjectUtil;

public class BeanResponse<T> extends ResponseParse {

    private Class<T> mCls;

    public BeanResponse(Class<T> cls){
        mCls = cls;
    }
    @Override
    public T parse(byte[] data) {
        return JsonObjectUtil.getBean(new String(data),mCls);
    }
}
