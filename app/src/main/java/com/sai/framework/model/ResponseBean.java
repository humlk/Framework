package com.sai.framework.model;

/**
 * Created by huajie on 2016/5/24.
 */
public class ResponseBean {
    private int result;
    private int code;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("code=").append(code)
                .append(",result=").append(result);
        return builder.toString();
    }
}
