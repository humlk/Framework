package com.sai.monitor.agent.strict;

public interface DetectType {
    interface Thread {

    }

    interface Vm{
        int CURSOR_LEAKS = 1;
        int CLOSABLE_LEAKS = 2;
        int ACTIVITY_LEAKS = 4;
        int INSTANCE_LEAKS = 8;
    }
}
