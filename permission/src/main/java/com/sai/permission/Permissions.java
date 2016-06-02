package com.sai.permission;

/**
 * 1. android permission
 * https://developer.android.com/reference/android/Manifest.permission.html
 * https://developer.android.com/guide/topics/security/permissions.html
 * https://developer.android.com/guide/topics/security/normal-permissions.html
 *
 * 2. 权限使用
 *  2.1 <user-permission android:name="" android:maxSdkVersion=""/>
 *  为了使当前应用可以顺利运行，需要申请系统访问权限。该权限申请后整个应用中的所有组件都有该权限的访问权。
 *  6.0以下，在manifest.xml中申请，在应用安装时会被系统赋予权限；6.0上则需要运行时动态申请。
 *
 *  android:name 权限名称，可以是系统提供的权限，也可以是使用其他应用使用<permission/>声明的权限（用于应用间访问）。
 *  android:maxSdkVersion 该权限支持的最大版本号，超过该版本后就不需要该权限。
 *
 *  2.2<permission/>
 *  自定义权限
 */
public interface Permissions {

    public interface Level{
        /** 只需要在Manifest.xml中配置就可以 **/
        public int NORMAL = 0;
        /** 6.0下只需要在Manifest.xml中配置；6.0上需要动态申请 **/
        public int DANGEROUS = 1;
        /** 需要应用间具有相同的签名 **/
        public int SIGNATURE = 2;
        /** 签名必须是系统签名 有可能需要android:sharedUserId="android.uid.system"**/
        public int SIGNATURE_OR_SYSTEM = 3;
        /** 在权限描述中有一类权限是：Not for use by third-party applications.
         * 一般的应用来源：
         *      android开源项目(这个是由google发起的AOSP)和google自己的应用
         *      手机制造商内置的应用
         *      除此了这些内置的应用之外的应用（这个就是第三方应用）
         * **/
    }

    public interface Dangerous {

        /**
         * 同一组的任何一个权限被授权了,其他权限也自动被授权.
         */

        /**
         * 日程信息
         **/
        public interface CALENDAR {
            public final String GROUP = "android.permission-group.CALENDAR";
            public final String READ_CALENDAR = "android.permission.READ_CALENDAR";
            public final String WRITE_CALENDAR = "android.permission.WRITE_CALENDAR";
        }

        /**
         * 相机
         **/
        public interface CAMERA {
            public final String GROUP = "android.permission-group.CAMERA";
            public final String CAMERA = "android.permission.CAMERA";
        }

        /**
         * 联系人
         **/
        public interface CONTACTS {
            public final String GROUP = "android.permission-group.CONTACTS";
            public final String READ_CONTACTS = "android.permission.READ_CONTACTS";
            public final String WRITE_CONTACTS = "android.permission.WRITE_CONTACTS";
            /** google的账户信息 **/
            public final String GET_ACCOUNTS = "android.permission.GET_ACCOUNTS";
        }

        /**
         * 位置信息
         **/
        public interface LOCATION {
            public final String GROUP = "android.permission-group.LOCATION";
            /**
             * 允许应用程序通过GPS卫星定位获取用户位置相关信息
             **/
            public final String ACCESS_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION";
            /**
             * 允许应用程序通过WiFi或基站的方式定位获取用户位置相关信息
             **/
            public final String ACCESS_COARSE_LOCATION = "android.permission.ACCESS_COARSE_LOCATION";
        }

        /**
         * 麦克风
         **/
        public interface MICROPHONE {
            public final String GROUP = "android.permission-group.MICROPHONE";
            /** 录制声音 **/
            public final String RECORD_AUDIO = "android.permission.RECORD_AUDIO";
        }

        /**
         * 手机
         **/
        public interface PHONE {
            public final String GROUP = "android.permission-group.PHONE";
            /** 读取电话状态 **/
            public final String READ_PHONE_STATE = "android.permission.READ_PHONE_STATE";
            /** 允许应用程序拨打电话,从非系统拨号器里初始化一个电话拨号,一般是通过代码直接拨号 **/
            public final String CALL_PHONE = "android.permission.CALL_PHONE";
            /** 读取通话记录 **/
            public final String READ_CALL_LOG = "android.permission.READ_CALL_LOG";
            /** 写入通话记录 **/
            public final String WRITE_CALL_LOG = "android.permission.WRITE_CALL_LOG";
            /** 使用语音邮件 **/
            public final String ADD_VOICEMAIL = "com.android.voicemail.permission.ADD_VOICEMAIL";
            /** 使用SIP视频服务 **/
            public final String USE_SIP = "android.permission.USE_SIP";
            /** 允许应用程序监视、修改外拨电话 **/
            public final String PROCESS_OUTGOING_CALLS = "android.permission.PROCESS_OUTGOING_CALLS";
        }

        /**
         * 传感器
         **/
        public interface SENSORS {
            public final String GROUP = "android.permission-group.SENSORS";
            /** 访问传感器 **/
            public final String BODY_SENSORS = "android.permission.BODY_SENSORS";
        }

        /**
         * 短信
         **/
        public interface SMS {
            public final String GROUP = "android.permission-group.SMS";
            /** 发送短信 **/
            public final String SEND_SMS = "android.permission.SEND_SMS";
            /** 接收短信 **/
            public final String RECEIVE_SMS = "android.permission.RECEIVE_SMS";
            /** 读取短信 **/
            public final String READ_SMS = "android.permission.READ_SMS";
            /** 接收WAP PUSH信息 **/
            public final String RECEIVE_WAP_PUSH = "android.permission.RECEIVE_WAP_PUSH";
            /** 接收彩信 **/
            public final String RECEIVE_MMS = "android.permission.RECEIVE_MMS";
        }

        /**
         * 存储
         **/
        public interface STORAGE {
            public final String GROUP = "android.permission-group.STORAGE";
            /** 读取扩展存储器 sd卡**/
            public final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";
            /** 写入扩展存储器 **/
            public final String WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";
        }
    }


    public interface Normal {

        public final String ACCESS_LOCATION_EXTRA_COMMANDS = "android.permission.ACCESS_LOCATION_EXTRA_COMMANDS";
        /** 获取网络信息状态，如当前的网络连接是否有效 **/
        public final String ACCESS_NETWORK_STATE = "android.permission.ACCESS_NETWORK_STATE";
        /** 读取wifi状态 **/
        public final String ACCESS_WIFI_STATE = "android.permission.ACCESS_WIFI_STATE";
        /** 允许程序连接配对过的蓝牙设备 **/
        public final String BLUETOOTH = "android.permission.BLUETOOTH";
        /** 允许程序进行发现和配对新的蓝牙设备 **/
        public final String BLUETOOTH_ADMIN = "android.permission.BLUETOOTH_ADMIN";
        /** 允许一个程序收到广播后快速收到下一个广播 **/
        public final String BROADCAST_STICKY = "android.permission.BROADCAST_STICKY";
        /** 允许应用程序改变网络连接状态 **/
        public final String CHANGE_NETWORK_STATE = "android.permission.CHANGE_NETWORK_STATE";
        /** 允许应用程序设置WiFi连接多模式 **/
        public final String CHANGE_WIFI_MULTICAST_STATE = "android.permission.CHANGE_WIFI_MULTICAST_STATE";
        /** 允许应用程序改变Wi-Fi连接状态 **/
        public final String CHANGE_WIFI_STATE = "android.permission.CHANGE_WIFI_STATE";
        /** 允许程序禁用键盘锁 **/
        public final String DISABLE_KEYGUARD = "android.permission.DISABLE_KEYGUARD";
        /** 允许应用程序扩展或收缩状态栏 **/
        public final String EXPAND_STATUS_BAR = "android.permission.EXPAND_STATUS_BAR";
        /** 闪光灯 **/
        public final String FLASHLIGHT = "android.permission.FLASHLIGHT";
        /** 允许应用程序获取应用的文件大小 **/
        public final String GET_PACKAGE_SIZE = "android.permission.GET_PACKAGE_SIZE";
        /** 在桌面上创建快捷链接 **/
        public final String INSTALL_SHORTCUT = "com.android.launcher.permission.INSTALL_SHORTCUT";
        /** 删除桌面上的快捷链接 **/
        public final String UNINSTALL_SHORTCUT = "com.android.launcher.permission.UNINSTALL_SHORTCUT";
        /** 允许访问网络 **/
        public final String INTERNET = "android.permission.INTERNET";
        /** 允许应用程序调用killBackgroundProcesses(String)方法结束后台进程 **/
        public final String KILL_BACKGROUND_PROCESSES = "android.permission.KILL_BACKGROUND_PROCESSES";
        /** 允许应用程序修改全局音频设置 **/
        public final String MODIFY_AUDIO_SETTINGS = "android.permission.MODIFY_AUDIO_SETTINGS";
        /** 允许应用程序执行NFC **/
        public final String NFC = "android.permission.NFC";
        /** 允许应用程序读取同步设置，读取Google在线同步设置 **/
        public final String READ_SYNC_SETTINGS = "android.permission.READ_SYNC_SETTINGS";
        /** 允许应用程序读取同步状态，读取Google在线同步设置 **/
        public final String READ_SYNC_STATS = "android.permission.READ_SYNC_STATS";
        /** 写入Google在线同步设置 **/
        public final String WRITE_SYNC_SETTINGS = "android.permission.WRITE_SYNC_SETTINGS";
        /** 允许应用程序开机自动运行 **/
        public final String RECEIVE_BOOT_COMPLETED = "android.permission.RECEIVE_BOOT_COMPLETED";
        /** 允许应用程序改变Z轴排列任务 **/
        public final String REORDER_TASKS = "android.permission.REORDER_TASKS";
        /** 允许应用程序设置闹铃提醒 **/
        public final String SET_ALARM = "com.android.alarm.permission.SET_ALARM";
        /** 允许应用程序设置系统时区 **/
        public final String SET_TIME_ZONE = "android.permission.SET_TIME_ZONE";
        /** 允许应用程序设置桌面壁纸 **/
        public final String SET_WALLPAPER = "android.permission.SET_WALLPAPER";
        /** 允许应用程序设置壁纸建议 **/
        public final String SET_WALLPAPER_HINTS = "android.permission.SET_WALLPAPER_HINTS";
        /** 允许使用设备的红外发射器 **/
        public final String TRANSMIT_IR = "android.permission.TRANSMIT_IR";
        /** 允许程序振动 **/
        public final String VIBRATE = "android.permission.VIBRATE";
        /** 允许程序在手机屏幕关闭后后台进程仍然运行 **/
        public final String WAKE_LOCK = "android.permission.WAKE_LOCK";


        /** 指纹识别 **/
        public final String USE_FINGERPRINT = "android.permission.USE_FINGERPRINT";
        /** 允许应用程序安装其他应用 6.0上需要此权限 **/
        public final String REQUEST_INSTALL_PACKAGES = "android.permission.REQUEST_INSTALL_PACKAGES";



        /*-----------------------------default---------------------------------------*/
        /** 读取电池信息 **/
        public final String BATTERY_STATS = "android.permission.BATTERY_STATS";
        /** 允许修改当前设置 如本地化 **/
        public final String CHANGE_CONFIGURATION = "android.permission.CHANGE_CONFIGURATION";


        /** 6.0开始，如果有相同的签名，就不需要申请权限GET_ACCOUNTS**/
        public final String GET_ACCOUNTS_PRIVILEGED = "android.permission.GET_ACCOUNTS_PRIVILEGED";


    }

    public interface Signature{
        /** Must be required by an AccessibilityService, to ensure that only the system can bind to it. **/
        public final String BIND_ACCESSIBILITY_SERVICE = "android.permission.BIND_ACCESSIBILITY_SERVICE";
        /** Must be required by a ChooserTargetService, to ensure that only the system can bind to it. **/
        public final String BIND_CHOOSER_TARGET_SERVICE = "android.permission.BIND_CHOOSER_TARGET_SERVICE";
        public final String BIND_DEVICE_ADMIN = "android.permission.BIND_DEVICE_ADMIN";
        public final String BIND_DREAM_SERVICE = "android.permission.BIND_DREAM_SERVICE";
        public final String BIND_INPUT_METHOD = "android.permission.BIND_INPUT_METHOD";
        public final String BIND_MIDI_DEVICE_SERVICE = "android.permission.BIND_MIDI_DEVICE_SERVICE";
        public final String BIND_NFC_SERVICE = "android.permission.BIND_NFC_SERVICE";
        public final String BIND_NOTIFICATION_LISTENER_SERVICE = "android.permission.BIND_NOTIFICATION_LISTENER_SERVICE";
        public final String BIND_PRINT_SERVICE = "android.permission.BIND_PRINT_SERVICE";
        public final String BIND_TEXT_SERVICE = "android.permission.BIND_TEXT_SERVICE";
        public final String BIND_TV_INPUT = "android.permission.BIND_TV_INPUT";
        public final String BIND_VOICE_INTERACTION = "android.permission.BIND_VOICE_INTERACTION";
        public final String BIND_VPN_SERVICE = "android.permission.BIND_VPN_SERVICE";
        public final String MANAGE_DOCUMENTS = "android.permission.MANAGE_DOCUMENTS";
        public final String SYSTEM_ALERT_WINDOW = "android.permission.SYSTEM_ALERT_WINDOW";
        public final String WRITE_SETTINGS = "android.permission.WRITE_SETTINGS";


    }

    public interface System{
        public final String CLEAR_APP_CACHE = "android.permission.CLEAR_APP_CACHE";
        public final String BIND_CARRIER_SERVICES = "android.permission.BIND_CARRIER_SERVICES";
        public final String BIND_INCALL_SERVICE = "android.permission.BIND_INCALL_SERVICE";
        public final String BIND_TELECOM_CONNECTION_SERVICE = "android.permission.BIND_TELECOM_CONNECTION_SERVICE";
        public final String BIND_WALLPAPER = "android.permission.BIND_WALLPAPER";
        public final String PACKAGE_USAGE_STATS = "android.permission.PACKAGE_USAGE_STATS";
        public final String BIND_REMOTEVIEWS = "android.permission.BIND_REMOTEVIEWS";
        public final String GLOBAL_SEARCH = "android.permission.GLOBAL_SEARCH";
        public final String READ_FRAME_BUFFER = "android.permission.READ_FRAME_BUFFER";
        public final String READ_VOICEMAIL = "com.android.voicemail.permission.READ_VOICEMAIL";
        public final String WRITE_VOICEMAIL = "com.android.voicemail.permission.WRITE_VOICEMAIL";

    }

    public interface NotThirdParty{
        public final String MASTER_CLEAR = "android.permission.MASTER_CLEAR";
        public final String ACCOUNT_MANAGER = "android.permission.ACCOUNT_MANAGER";
        public final String ACCESS_CHECKIN_PROPERTIES = "android.permission.ACCESS_CHECKIN_PROPERTIES";
        public final String BIND_APPWIDGET = "android.permission.BIND_APPWIDGET";
        public final String BLUETOOTH_PRIVILEGED = "android.permission.BLUETOOTH_PRIVILEGED";
        public final String BROADCAST_PACKAGE_REMOVED = "android.permission.BROADCAST_PACKAGE_REMOVED";
        public final String BROADCAST_SMS = "android.permission.BROADCAST_SMS";
        public final String BROADCAST_WAP_PUSH = "android.permission.BROADCAST_WAP_PUSH";
        public final String CALL_PRIVILEGED = "android.permission.CALL_PRIVILEGED";
        public final String CAPTURE_AUDIO_OUTPUT = "android.permission.CAPTURE_AUDIO_OUTPUT";
        public final String CAPTURE_SECURE_VIDEO_OUTPUT = "android.permission.CAPTURE_SECURE_VIDEO_OUTPUT";
        public final String CAPTURE_VIDEO_OUTPUT = "android.permission.CAPTURE_VIDEO_OUTPUT";
        public final String CHANGE_COMPONENT_ENABLED_STATE = "android.permission.CHANGE_COMPONENT_ENABLED_STATE";
        public final String CONTROL_LOCATION_UPDATES = "android.permission.CONTROL_LOCATION_UPDATES";
        public final String DELETE_CACHE_FILES = "android.permission.DELETE_CACHE_FILES";
        public final String DELETE_PACKAGES = "android.permission.DELETE_PACKAGES";
        public final String DIAGNOSTIC = "android.permission.DIAGNOSTIC";
        public final String DUMP = "android.permission.DUMP";
        public final String FACTORY_TEST = "android.permission.FACTORY_TEST";
        public final String INSTALL_LOCATION_PROVIDER = "android.permission.INSTALL_LOCATION_PROVIDER";
        public final String INSTALL_PACKAGES = "android.permission.INSTALL_PACKAGES";
        public final String LOCATION_HARDWARE = "android.permission.LOCATION_HARDWARE";
        public final String MEDIA_CONTENT_CONTROL = "android.permission.MEDIA_CONTENT_CONTROL";
        public final String MODIFY_PHONE_STATE = "android.permission.MODIFY_PHONE_STATE";
        public final String MOUNT_FORMAT_FILESYSTEMS = "android.permission.MOUNT_FORMAT_FILESYSTEMS";
        public final String MOUNT_UNMOUNT_FILESYSTEMS = "android.permission.MOUNT_UNMOUNT_FILESYSTEMS";

        public final String READ_LOGS = "android.permission.READ_LOGS";
        public final String REBOOT = "android.permission.REBOOT";
        public final String SEND_RESPOND_VIA_MESSAGE = "android.permission.SEND_RESPOND_VIA_MESSAGE";
        public final String SET_ALWAYS_FINISH = "android.permission.SET_ALWAYS_FINISH";
        public final String SET_ANIMATION_SCALE = "android.permission.SET_ANIMATION_SCALE";
        public final String SET_DEBUG_APP = "android.permission.SET_DEBUG_APP";
        public final String SET_PROCESS_LIMIT = "android.permission.SET_PROCESS_LIMIT";
        public final String SET_TIME = "android.permission.SET_TIME";
        public final String SIGNAL_PERSISTENT_PROCESSES = "android.permission.SIGNAL_PERSISTENT_PROCESSES";
        public final String STATUS_BAR = "android.permission.STATUS_BAR";
        public final String UPDATE_DEVICE_STATS = "android.permission.UPDATE_DEVICE_STATS";
        public final String WRITE_APN_SETTINGS = "android.permission.WRITE_APN_SETTINGS";
        public final String WRITE_GSERVICES = "android.permission.WRITE_GSERVICES";
        public final String WRITE_SECURE_SETTINGS = "android.permission.WRITE_SECURE_SETTINGS";
    }

}
