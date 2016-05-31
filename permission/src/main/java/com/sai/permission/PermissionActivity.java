package com.sai.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class PermissionActivity extends AppCompatActivity{

    private final String TAG = "PermissionActivity";

    private static final String Key_Permissions = "permissions";

    private int requesCode = 001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static void launch(Activity activity, String[] permissions){
        Intent intent = new Intent(activity,PermissionActivity.class);
        intent.putExtra(Key_Permissions,permissions);
        activity.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(PermissionManager.check(getApplicationContext(),getPermissions())){
            Log.d(TAG,"has permissions");
        }else{
            Log.d(TAG,"do not has permissions");
            PermissionManager.request(this,requesCode,getPermissions());
        }
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
            finish();
        }else{
            Log.d(TAG, "donot have permission");
            //提示用户申请权限
        }
    }
}
