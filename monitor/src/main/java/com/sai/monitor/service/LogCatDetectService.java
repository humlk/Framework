package com.sai.monitor.service;

import android.app.IntentService;
import android.content.Intent;

import com.sai.monitor.agent.logcat.LogDetector;
import com.sai.monitor.agent.logcat.LogInfo;
import com.sai.monitor.agent.logcat.LogInterceptor;

/**
 * 用于拦截
 */
public class LogCatDetectService extends IntentService {
    private static String mName = "logcat";

    public static final String KEY_TAGS  = "tags";

    public LogCatDetectService() {
        super(mName);
    }

    private LogInterceptor mLogcatInterceptor;
    @Override
    protected void onHandleIntent(Intent intent) {
        String[] tags = intent.getStringArrayExtra(KEY_TAGS);
        mLogcatInterceptor = new LogInterceptor();
        mLogcatInterceptor.setTag(tags).setDetector(new LogDetector() {
            @Override
            public boolean onDetected(LogInfo log) {
                return onLogDetected(log);
            }
        }).start();
    }


    private boolean onLogDetected(LogInfo log){
        return true;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mLogcatInterceptor != null){
            mLogcatInterceptor.stop();
            mLogcatInterceptor = null;
        }
    }
}
