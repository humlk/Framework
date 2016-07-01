package com.sai.monitor.agent.logcat;

/**
 * 用于探测日志是否是自己需要的
 */
public interface LogDetector {

    /**
     * 判断日志是否需要处理
     * @param log
     * @return
     */
    public boolean onDetected(LogInfo log);
}
