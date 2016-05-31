package com.sai.security.store;


import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;

import com.sai.security.store.base.SecurityStore;

public class ShareStore extends SecurityStore{

    private String mDomain;
    private Application mContext;
    private String sp_key = "sai_sp_key";

    public ShareStore(Application context, String domain){
        mContext = context;
        mDomain = domain;
        SharedPreferences sp = mContext.getSharedPreferences(mDomain, Activity.MODE_PRIVATE);
        String str = sp.getString(sp_key, null);
        if(str != null && str.length() > 0){
            byte[] data = dn(str.getBytes());
            if(data != null){
                create(new String(data));
            }else{
                create();
            }
        }else{
            create();
        }
    }

    public ShareStore(Application context){
        this(context, "sai_sp");
    }

    @Override
    public void commit() {
        SharedPreferences sp = mContext.getSharedPreferences(mDomain, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit().putString(sp_key,new String(en(getData().getBytes())));
        editor.commit();
    }
}
