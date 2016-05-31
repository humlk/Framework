package com.sai.security.store;

import android.app.Application;

import com.sai.base.util.AppUtil;
import com.sai.base.util.FileUtil;
import com.sai.security.store.base.SecurityStore;


public class FileStore extends SecurityStore{

    private String path;

    public FileStore(Application context){
        path = AppUtil.getStorePath(context)+"/fs.dat";

        try {
            byte[] data = FileUtil.getFileContent(path);
            if(data != null){
                create(new String(dn(data)));
                return;
            }
        } catch (Exception e) {

        }

        create();
    }

    @Override
    public void commit() {

        byte[] data = getData().getBytes();
        try {
            FileUtil.saveToFileByBytes(path,en(data));
        } catch (Exception e) {
        }
    }
}
