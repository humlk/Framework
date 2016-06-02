package com.sai.net.http;

import android.os.Handler;
import android.os.Looper;

import com.sai.net.SaiRepository;
import com.sai.net.cache.Cache;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 请求队列
 */
public class RequestQueue {

    /**
     * 线程安全的计数器
     **/
    private AtomicInteger mSequenceGenerator = new AtomicInteger();

    private final Map<String, Queue<Request<?>>> mWaitingRequests =
            new HashMap<String, Queue<Request<?>>>();

    private final Set<Request<?>> mCurrentRequests = new HashSet<Request<?>>();

//    /** The cache triage queue. */
//    private final PriorityBlockingQueue<Request<?>> mCacheQueue =
//        new PriorityBlockingQueue<Request<?>>();

    /**
     * 使用优先级阻塞队列，优先级的判断通过构造函数(Request)传入的Compator对象来决定
     */
    private final PriorityBlockingQueue<Request<?>> mNetworkQueue =
            new PriorityBlockingQueue<Request<?>>();

    /**
     * 线程池大小
     */
    private static final int DEFAULT_NETWORK_THREAD_POOL_SIZE = 4;

//    private final Cache mCache;
//
//    private final Network mNetwork;

    /**
     * 对请求响应进行分发
     */
    private final ResponseDelivery mDelivery;

    /**
     * 网络调度
     */
    private NetworkDispatcher[] mDispatchers;

    private SaiRepository mRepository;

//    /** 缓存调度 */
//    private CacheDispatcher mCacheDispatcher;


    public RequestQueue(Cache cache, Network network, int threadPoolSize,
                        ResponseDelivery delivery) {
//        mCache = cache;
//        mNetwork = network;
        mDispatchers = new NetworkDispatcher[threadPoolSize];
        mDelivery = delivery;
    }

    //    public RequestQueue(Cache cache, Network network, int threadPoolSize) {
//        this(cache, network, threadPoolSize,
//                new ExecutorDelivery(new Handler(Looper.getMainLooper())));
//    }
//
//    public RequestQueue(Cache cache, Network network) {
//        this(cache, network, DEFAULT_NETWORK_THREAD_POOL_SIZE);
//    }
    public RequestQueue(SaiRepository repository, int threadPoolSize, ResponseDelivery delivery) {
        mRepository = repository;
        mDispatchers = new NetworkDispatcher[threadPoolSize];
        mDelivery = delivery;
    }

    public RequestQueue(SaiRepository repository) {
        this(repository, DEFAULT_NETWORK_THREAD_POOL_SIZE, new ExecutorDelivery(new Handler(Looper.getMainLooper())));
    }

    public void start() {
        //停止当前所有的请求
        stop();

        // 创建并启动线程池中的线程
        for (int i = 0; i < mDispatchers.length; i++) {
            NetworkDispatcher networkDispatcher = new NetworkDispatcher(mNetworkQueue, mRepository, mDelivery);
            mDispatchers[i] = networkDispatcher;
            networkDispatcher.start();
        }
    }

    public void stop() {

        for (int i = 0; i < mDispatchers.length; i++) {
            if (mDispatchers[i] != null) {
                mDispatchers[i].quit();
            }
        }
    }

    public int getSequenceNumber() {
        return mSequenceGenerator.incrementAndGet();
    }

//    public Cache getCache() {
//        return mCache;
//    }

    public interface RequestFilter {
        public boolean apply(Request<?> request);
    }

    public void cancelAll(RequestFilter filter) {
        synchronized (mCurrentRequests) {
            for (Request<?> request : mCurrentRequests) {
                if (filter.apply(request)) {
                    request.cancel();
                }
            }
        }
    }

    public void cancelAll(final Object tag) {
        if (tag == null) {
            throw new IllegalArgumentException("Cannot cancelAll with a null tag");
        }
        cancelAll(new RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return request.getTag() == tag;
            }
        });
    }

    public <T> Request<T> add(Request<T> request) {
        // 将request和RequestQueue进行绑定，当request结束时可以通知RequestQueue。
        request.setRequestQueue(this);

        //请求准备启动，这时请求不一定会启动，有可能是缓存就不会启动，但是对UI来说不知道后台的操作，所以这里不管什么情况都会通知UI。
        request.deliverPerStart();

        //正在运行的request
        synchronized (mCurrentRequests) {
            mCurrentRequests.add(request);
        }
        // 优先级
        request.setSequence(getSequenceNumber());

        //目前都不缓存
        mNetworkQueue.add(request);
        return request;


//        // 检查是否需要缓存，不缓存则直接运行
//        if (!request.shouldCache()) {
//            mNetworkQueue.add(request);
//            return request;
//        }

//        //如果有相同的地址请求，则进行等待状态
//        synchronized (mWaitingRequests) {
//            //默认根据url缓存
//            String cacheKey = request.getCacheKey();
//            if (mWaitingRequests.containsKey(cacheKey)) {
//                //处理同一url多个请求
//                Queue<Request<?>> stagedRequests = mWaitingRequests.get(cacheKey);
//                if (stagedRequests == null) {
//                    stagedRequests = new LinkedList<Request<?>>();
//                }
//                stagedRequests.add(request);
//                mWaitingRequests.put(cacheKey, stagedRequests);
//            } else {
//                //直接去缓存获取数据
//                mWaitingRequests.put(cacheKey, null);
//                mCacheQueue.add(request);
//            }
//            return request;
//        }
    }


    <T> void finish(Request<T> request) {
        synchronized (mCurrentRequests) {
            mCurrentRequests.remove(request);
        }
//        //请求结束后针对需要缓存的请求进行处理
//        if (request.shouldCache()) {
//            synchronized (mWaitingRequests) {
//                String cacheKey = request.getCacheKey();
//                Queue<Request<?>> waitingRequests = mWaitingRequests.remove(cacheKey);
//                if (waitingRequests != null) {
//                    mCacheQueue.addAll(waitingRequests);
//                }
//            }
//        }
    }

    public ResponseDelivery getDelivery() {
        return mDelivery;
    }
}
