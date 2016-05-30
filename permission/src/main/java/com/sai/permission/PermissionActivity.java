package com.sai.permission;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class PermissionActivity extends AppCompatActivity{

    private final String TAG = "PermissionActivity";

    private int requesCode = 001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        PermissionManager.request(this,requesCode, getPermissions());
    }

    private String[] getPermissions(){
        return new String[]{Manifest.permission.READ_CONTACTS};
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(requesCode != requesCode){
            return;
        }

        if(PermissionManager.check(grantResults)){
            //有权限了

            Log.d(TAG, "has permission");
        }else{

        }
    }
}
