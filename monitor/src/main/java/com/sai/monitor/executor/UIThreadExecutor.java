package com.sai.monitor.executor;


import android.os.Looper;
import android.util.Printer;

public class UIThreadExecutor {

    private UIPrinter mUIPrinter;

    private static class Instance {
        public static UIThreadExecutor instance = new UIThreadExecutor();
    }

    private UIThreadExecutor() {
        mUIPrinter = new UIPrinter();
    }

    public UIThreadExecutor get() {
        return Instance.instance;
    }

    public void start() {
        Looper.getMainLooper().setMessageLogging(mUIPrinter);
    }

    public void stop() {
        Looper.getMainLooper().setMessageLogging(null);
    }

    public static class UIPrinter implements Printer {

        @Override
        public void println(String x) {

        }

        private boolean isStart(String x) {
            if (x.startsWith(">>>>> Dispatching to")) {
                return true;
            }
            return false;
        }

        private boolean isStop(String x) {
            if (x.startsWith("<<<<< Finished to")) {
                return true;
            }
            return false;
        }
    }


}
