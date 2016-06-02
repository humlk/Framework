package com.sai.net.http;

import android.os.Process;
import android.os.SystemClock;

import com.sai.net.SaiRepository;
import com.sai.net.exception.SaiException;
import com.sai.net.util.LogUtil;

import java.util.concurrent.BlockingQueue;

/**
 * 进行网络调度
 */
public class NetworkDispatcher extends Thread {
    /**
     * 使用阻塞队列，当队列为空的情况下，消费线程就会阻塞；满时，生产线程阻塞
     */
    private final BlockingQueue<Request<?>> mQueue;
//    private final Network mNetwork;
//    private final Cache mCache;
    private final ResponseDelivery mDelivery;

    private SaiRepository mRepository;
    /**
     * 是否正在运行
     */
    private volatile boolean mRunning = true;


//    public NetworkDispatcher(BlockingQueue<Request<?>> queue,
//                             Network network, Cache cache,
//                             ResponseDelivery delivery) {
//        mQueue = queue;
//        mNetwork = network;
//        mCache = cache;
//        mDelivery = delivery;
//    }

    public NetworkDispatcher(BlockingQueue<Request<?>> queue,
                             SaiRepository repository,
                             ResponseDelivery delivery){
        mQueue = queue;
        mRepository = repository;
        mDelivery = delivery;
    }

    public void quit() {
        mRunning = false;
        interrupt();
    }

//    /**
//     * 进行流量监控
//     * @param request
//     */
//    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
//    private void addTrafficStatsTag(Request<?> request) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
//            TrafficStats.setThreadStatsTag(request.getTrafficStatsTag());
//            TrafficStats.clearThreadStatsTag();
//
//        }
//    }

    @Override
    public void run() {
        //线程优先级，处于后台，这样就不会影响前台的UI线程
        /**
         int THREAD_PRIORITY_AUDIO //标准音乐播放使用的线程优先级
         int THREAD_PRIORITY_BACKGROUND //标准后台程序
         int THREAD_PRIORITY_DEFAULT // 默认应用的优先级
         int THREAD_PRIORITY_DISPLAY //标准显示系统优先级，主要是改善UI的刷新
         int THREAD_PRIORITY_FOREGROUND //标准前台线程优先级
         int THREAD_PRIORITY_LESS_FAVORABLE //低于favorable
         int THREAD_PRIORITY_LOWEST //有效的线程最低的优先级
         int THREAD_PRIORITY_MORE_FAVORABLE //高于favorable
         int THREAD_PRIORITY_URGENT_AUDIO //标准较重要音频播放优先级
         int THREAD_PRIORITY_URGENT_DISPLAY //标准较重要显示优先级，对于输入事件同样适用。
         */
        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
        while (mRunning) {
            long startTimeMs = SystemClock.elapsedRealtime();
            Request<?> request;
            try {
                // 从队列中获取一个请求.
                request = mQueue.take();
            } catch (InterruptedException e) {
                if (!mRunning) {
                    return;
                }
                continue;
            }
            //请求取消了
            if (request.isCanceled()) {
                LogUtil.d("请求被取消");
                mDelivery.postCancel(request);
                continue;
            }

            LogUtil.show("请求开始执行");
            try {
                // 执行请求
                Response<?> response = mRepository.getResponse(request);
                //执行交付工作
                mDelivery.postResponse(request, response);
            } catch (SaiException exception) {
                exception.setNetworkTimeMs(SystemClock.elapsedRealtime() - startTimeMs);
                parseAndDeliverNetworkError(request, exception);
            }
        }
    }

    private void parseAndDeliverNetworkError(Request<?> request, SaiException error) {
        error = request.parseNetworkError(error);
        mDelivery.postError(request, error);
    }
}
