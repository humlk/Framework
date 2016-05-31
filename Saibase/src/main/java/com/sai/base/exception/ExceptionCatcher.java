package com.sai.base.exception;

import java.lang.Thread.UncaughtExceptionHandler;

public class ExceptionCatcher {

    private OnCaughtExceptionListener mExceptionListener;

    private ExceptionCatcher() {
    }

    private static class ECInstance {
        public static ExceptionCatcher instance = new ExceptionCatcher();
    }

    public ExceptionCatcher getInstance() {
        return ECInstance.instance;
    }

    private UncaughtExceptionHandler handler = new UncaughtExceptionHandler() {

        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            if(mExceptionListener != null){
                mExceptionListener.onCaught(ex.getMessage());
            }
//            System.out.println(ex.getMessage());
//            crashMsg = "很抱歉，程序出现异常，即将退出！";

//            new Thread() {
//                public void run() {
//                    Toast.makeText(mContext, crashMsg, Toast.LENGTH_SHORT).show();
//                }
//            }.start();
//
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//
//            }
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
