package com.sai.monitor.exception;

import java.lang.Thread.UncaughtExceptionHandler;

public class ExceptionCatcher {

    private OnCaughtExceptionListener mExceptionListener;

    private ExceptionCatcher() {
    }

    private static class ECInstance {
        public static ExceptionCatcher instance = new ExceptionCatcher();
    }

    public ExceptionCatcher get() {
        return ECInstance.instance;
    }

    private UncaughtExceptionHandler handler = new UncaughtExceptionHandler() {

        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            if(mExceptionListener != null){
                mExceptionListener.onCaught(ex.getMessage());
            }
////            Context context = Gloria.getContext();
////            AppUtil.reStartApp(context);
//            android.os.Process.killProcess(android.os.Process.myPid());
//            System.exit(0);
        }
    };

    /**
     * 初始化
     */
    public void start(OnCaughtExceptionListener listener) {
        Thread.setDefaultUncaughtExceptionHandler(handler);
    }

    public interface OnCaughtExceptionListener{
        public void onCaught(String message);
    }
}
