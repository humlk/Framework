package com.sai.monitor.agent.logcat;

import com.sai.monitor.log.MonitorLog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于拦截logcat
 */
public class LogInterceptor {

    private static final String COMMAND_CLEAR = "logcat -c";
    private static final String COMMAND_LOG_PRE = "logcat";
    /**
     * 系统错误的日志标签
     **/
//    public static final String TAG_SYSTEM_ERR = "System.err";

    /**
     * 匹配规则 BitmapFactory( 4468):
     **/
//    private static final String TAG_REG_FORMAT = "(%s)(\\(\\d{1,}\\))?:";
    private static final String TAG_REG_FORMAT = "(%s)(\\([0-9]+\\))?:";
    /**
     * 日志标签
     **/
    private String mTag;
    /**
     * 日志标签的正则表达式
     **/
    private String mTagReg;

    private Process mProcess = null;

    private List<String> mTagList = null;
    private LogDetector mLogDetector = null;

    public static LogInterceptor get(){
        return null;
    }

    public LogInterceptor() {
        mTagList = new ArrayList<>();
//        mTagList.add(TAG_SYSTEM_ERR);
    }

    public LogInterceptor addTag(String... tags){
        for(String tag: tags){
            if (!isEmpty(tag) && !mTagList.contains(tag)) {
                mTagList.add(tag);
            }
        }

        return this;
    }

    public LogInterceptor setTag(String... tags){
        clearTag();
        addTag(tags);
        return this;
    }

    public LogInterceptor clearTag(){
        mTagList.clear();
        return this;
    }

    public LogInterceptor setDetector(LogDetector detector) {
        mLogDetector = detector;
        return this;
    }


    private void preStart(){
        StringBuilder tagBuilder = new StringBuilder();
        StringBuilder tagRegBuilder = new StringBuilder();
        for (String key : mTagList) {
            tagBuilder.append(" ").append(key);
            tagRegBuilder.append("|").append(key);
        }
        mTag = tagBuilder.toString();

        tagRegBuilder.deleteCharAt(0);
        mTagReg = tagRegBuilder.toString();
    }

    public void start() {
        stop();


        preStart();

        //清除日志
        processRun(COMMAND_CLEAR);

        //组装数据
        StringBuilder builder = new StringBuilder(COMMAND_LOG_PRE);
        //格式化
        //目前只支持按时间格式化
        builder.append(" -v time");

        //拦截的关键字
        if (!isEmpty(mTag)) {
            builder.append(" -s ").append(mTag);
        }
        MonitorLog.log(" LogInterceptor process start");
        //执行命令
        mProcess = processRun(builder.toString());
        if (mProcess == null) {
            MonitorLog.log(" LogInterceptor process is null");
            return;
        }
        MonitorLog.log(" LogInterceptor readLog -> start");
        readLog();

    }

    private Process processRun(String command) {
        MonitorLog.log(" LogInterceptor command -> " + command);
        try {
            return Runtime.getRuntime().exec(command);
        } catch (IOException e) {
//            e.printStackTrace();
            MonitorLog.log(" LogInterceptor Runtime exec error -> " + e.getMessage());
        }
        return null;
    }

    private void readLog() {

        //读取日志信息
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(mProcess.getInputStream()));
        if (bufferedReader == null) {
            MonitorLog.log(" LogInterceptor readLog error -> stream is null");
            return;
        }

        try {
            String readLine = bufferedReader.readLine();
            while (!isEmpty(readLine)) {
                readLine = bufferedReader.readLine();
                dispatchLog(readLine);
            }
        } catch (IOException e) {
            MonitorLog.log("LogInterceptor readLog -> "+e.getMessage());
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
            }
            bufferedReader = null;
        }
    }

    private void dispatchLog(String log) {
        MonitorLog.log(" LogInterceptor dispatchLog -> "+log);
        LogInfo logInfo = parseLog(log);
        if (mLogDetector != null) {
            mLogDetector.onDetected(logInfo);
        }
    }

    /**
     * 解析日志信息,日志信息分为三大部分： 时间 标签 日志。
     * 所以要按标签来进行分割
     *
     * @param log
     * @return
     */
    private LogInfo parseLog(String log) {
        if (isEmpty(log)) {
            return null;
        }
        String[] strs = log.split(String.format(TAG_REG_FORMAT, mTagReg));
        if (strs == null || strs.length == 0) {
            return null;
        }
        String time = getFromArray(strs, 0);
        String tag = getFromArray(strs, 1);
        String msg = getFromArray(strs, 2);
        return new LogInfo(time, tag, msg);
    }

    private String getFromArray(String[] strs, int index) {

        if (index < strs.length) {
            return strs[index];
        }
        return null;
    }

    private boolean isEmpty(String str) {
        if (str != null && str.length() > 0) {
            return false;
        }
        return true;
    }

    public void stop() {
        MonitorLog.log(" LogInterceptor process end");
        if (mProcess != null) {
            mProcess.destroy();
            mProcess = null;
        }
    }
}
