package com.sai.base.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** 
 * 系统名称： </br>
 * 模块名称： </br>
 * 功能说明： </br>
 * 
 * @ClassName:  ThreadUtil  
 * @author: huajie 
 * @version: 1.0
 * @date:   2015年1月6日 
 *      
 */
public class ThreadUtil {

    private ThreadUtil(){};
    private static ExecutorService service;
    static{
        service = Executors.newFixedThreadPool(5);
    }
    
    public static void run(Runnable runnable){
        service.execute(runnable);
    }
    
    public static void run(Thread thread){
        service.execute(thread);
    }
    
    public static void stop(){
        service.shutdown();
    }
    
//    public static Future<?> run(Runnable runnable){
//        return service.submit(runnable);
//    }
}
