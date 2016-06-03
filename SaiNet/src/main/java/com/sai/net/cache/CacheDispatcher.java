//package com.sai.net.cache;
//
//import android.os.Process;
//
//import com.sai.net.http.NetworkResponse;
//import com.sai.net.http.Request;
//import com.sai.net.http.Response;
//import com.sai.net.http.ResponseDelivery;
//
//import java.util.concurrent.BlockingQueue;
//
//public class CacheDispatcher extends Thread {
//
//    /**
//     * 缓存队列
//     */
//    private final BlockingQueue<Request<?>> mCacheQueue;
//
//    /**
//     * 网络请求队列
//     */
//    private final BlockingQueue<Request<?>> mNetworkQueue;
//
//    /**
//     * 缓存操作
//     */
//    private final Cache mCache;
//
//    /**
//     * 请求响应
//     */
//    private final ResponseDelivery mDelivery;
//
//    private volatile boolean mRunning = true;
//
//    public CacheDispatcher(
//            BlockingQueue<Request<?>> cacheQueue, BlockingQueue<Request<?>> networkQueue,
//            Cache cache, ResponseDelivery delivery) {
//        mCacheQueue = cacheQueue;
//        mNetworkQueue = networkQueue;
//        mCache = cache;
//        mDelivery = delivery;
//    }
//
//    public void quit() {
//        mRunning = false;
//        interrupt();
//    }
//
//    @Override
//    public void run() {
//        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
//
//        mCache.initialize();
//
//        while (true) {
//            try {
//                final Request<?> request = mCacheQueue.take();
//
//                if (request.isCanceled()) {
//                    mDelivery.postCancel(request);
//                    continue;
//                }
//
//                // 检查是否有需要缓存的对象
//                Cache.Entry entry = mCache.get(request.getCacheKey());
//                if (entry == null) {
//                    //没有要缓存的对象,扔给网络请求
//                    mNetworkQueue.put(request);
//                    continue;
//                }
//
//                // 缓存已过期
//                if (entry.isExpired()) {
//                    request.setCacheEntry(entry);
//                    mNetworkQueue.put(request);
//                    continue;
//                }
//
//                Response<?> response = request.parseNetworkResponse(
//                        new NetworkResponse(entry.data, entry.responseHeaders));
//
//                mDelivery.postResponse(request, response);
//
//            } catch (InterruptedException e) {
//                if (!mRunning) {
//                    return;
//                }
//                continue;
//            }
//        }
//    }
//}
