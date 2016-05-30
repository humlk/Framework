package com.sai.net;


interface SaiConstant {



    public interface NetState{
        /**
         * 网络状态标识：未联网
         */
        final int NULL = 0;

        /**
         * 网络状态标识：已连接手机网络(2G3G)
         */
        final int MOBILE = 1;

        /**
         * 网络状态标识：已连接wifi网络
         */
        final int WIFI = 2;
    }




}
