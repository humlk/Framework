package com.sai.security.store;

import android.app.Application;

public class StoreManager {

    private FileStore mFileStore = null;
    private ShareStore mShareStore = null;

    private Application mApplication = null;

    private final Object syncFile = new Object();
    private final Object syncShare = new Object();

    public static StoreManager get(){
        return Instance.sm;
    }

    private static class Instance{
        public static StoreManager sm = new StoreManager();
    }

    private StoreManager(){};

    public void init(Application application){
        mApplication = application;
    }

    public FileStore getFileStore(){
        if(mFileStore == null){
            synchronized (syncFile){
                if(mFileStore == null){
                    mFileStore = new FileStore(mApplication);
                }
            }
        }
        return mFileStore;
    }

    public ShareStore getShareStore(){
        if(mShareStore == null){
            synchronized (syncShare){
                if(mShareStore == null){
                    mShareStore = new ShareStore(mApplication);
                }
            }
        }
        return mShareStore;
    }
}
