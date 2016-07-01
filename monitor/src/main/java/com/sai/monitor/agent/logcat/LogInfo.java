package com.sai.monitor.agent.logcat;

/**
 * 日志信息
 *
 *
 * <br/>
 *
 * 日志格式：
 * 格式： time logLevel/tag(processId): msg
 *
 * logLevel:
 * V — 明细 verbose 优先级最低
 * D — 调试 debug
 * I — 信息 info
 * W — 警告 warn
 * E — 错误 error
 * F — 严重错误 fatal
 * S — 无记载 silent 该标记一般用于特定目标检测   logcat -s PowerManagerService*  搜索tag是以PowerManagerService开头的日志信息。
 * 如果只是按日志级别搜索： logcat *:w 搜索warn上的日志（包括warn）
 *
 * 列子：
 * -v time: 06-29 10:15:12.109 D/SecContentProvider2( 4245): uri = -1 selection = getSealedState
 * -v thread: D( 4245: 7021) uri = -1 selection = getSealedState
 *
 * 命令：
 * -v 格式化
 *  brief — 显示优先级/标记和原始进程的PID (默认格式)
 *  process — 仅显示进程PID
 *  tag — 仅显示优先级/标记
 *  thread — 仅显示进程：线程和优先级/标记
 *  raw — 显示原始的日志信息，没有其他的元数据字段
 *  time — 显示日期，调用时间，优先级/标记，PID
 *  long —显示所有的元数据字段并且用空行分隔消息内容
 *
 * -c 清理日志
 *
 * 目前默认按时间解析
 */
public class LogInfo {

    private String mTime;
    private String mTag;
    private String mMsg;

    public LogInfo(String time, String tag, String msg){
        mTime = time;
        mTag = tag;
        mMsg = msg;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public String getTag() {
        return mTag;
    }

    public void setTag(String tag) {
        mTag = tag;
    }

    public String getMsg() {
        return mMsg;
    }

    public void setMsg(String msg) {
        mMsg = msg;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(mTime).append(" ").append(mTag).append(" ").append(mMsg);
        return buffer.toString();
    }
}
