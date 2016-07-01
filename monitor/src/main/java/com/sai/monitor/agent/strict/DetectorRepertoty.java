package com.sai.monitor.agent.strict;

/**
 * 做个仓库用来管理所有的检测者
 */
public enum DetectorRepertoty {
    ACTIVITY_LEAK(DetectType.Vm.ACTIVITY_LEAKS),
    CLASS_INSTANCE_LEAK(DetectType.Vm.INSTANCE_LEAKS),

    DEFAULT(0);
    private final int mType;

    DetectorRepertoty(int type){
        mType = type;
    }
}
