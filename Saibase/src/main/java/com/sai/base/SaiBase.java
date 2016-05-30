package com.sai.base;


import android.content.Context;

public class SaiBase {

    private Context mContext;

    private SaiBase() {
    }

    private static class JudgeBaseInstance {
        public static SaiBase instance = new SaiBase();
    }

    public static SaiBase get() {
        return JudgeBaseInstance.instance;
    }

    public void init(Context context){
        mContext = context;

    }
    public Context getContext(){
        return mContext;
    }
}
