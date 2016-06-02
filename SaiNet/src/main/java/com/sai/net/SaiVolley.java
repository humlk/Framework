package com.sai.net;

import com.sai.net.http.RequestQueue;


class SaiVolley {

    private static SaiVolley Instance;

    private RequestQueue fileDownloadQueue;
//    private static RequestQueue imageQueue;
    private RequestQueue InstanceRequestQueue;
//
//    private static OkHttpStack mOkHttpUrlStack;
//    private static SSLSocketFactory sslSocketFactory = null;
//    private String mUserAgent;
//
//    private OkHttpClient mHttpClient;

    private final Object mFileSynObject = new Object();

    public static SaiVolley get() {
        if(Instance == null){
            synchronized (SaiVolley.class){
                if(Instance == null){
                    Instance = new SaiVolley();
                }
            }
        }
        return Instance;
    }
    private SaiVolley() {
//        mHttpClient = new OkHttpClient();
        InstanceRequestQueue = newRequestQueue();

    }

    public RequestQueue getRequestQueue() {
        return InstanceRequestQueue;
    }

    public RequestQueue getFileDownloadQueue(){
        if(fileDownloadQueue == null){
            synchronized (mFileSynObject){
                if(fileDownloadQueue == null){
                    fileDownloadQueue = createFileQueue();
                }
            }
        }
        return fileDownloadQueue;
    }

    private RequestQueue createFileQueue(){
//        OkHttpStack stack = new OkHttpStack(mHttpClient.newBuilder().build());
//        Network network = new BasicNetwork(stack);
        RequestQueue queue = new RequestQueue(SaiRepository.get());
        queue.start();
        return queue;
    }
    public RequestQueue newRequestQueue() {
//        File cacheDir = new File(SaiNet.getContext().getCacheDir(), DEFAULT_CACHE_DIR);
//
//        OkHttpStack stack = getDefaultHttpStack();
//        Network network = new BasicNetwork(stack);

        RequestQueue queue = new RequestQueue(SaiRepository.get());
        queue.start();
        return queue;
    }

//    public boolean isNetSafe(){
//        if(sslSocketFactory != null){
//            return true;
//        }
//        return false;
//    }

//    private OkHttpStack getDefaultHttpStack() {
//        if (mOkHttpUrlStack == null) {
//            sslSocketFactory = TrustSSLSocketFactory.initSSL(SaiNet.getContext());
//            OkHttpClient client = mHttpClient.newBuilder().sslSocketFactory(sslSocketFactory).build();
//            mOkHttpUrlStack = new OkHttpStack(client,sslSocketFactory);
//        }
//        return mOkHttpUrlStack;
//    }



//    public static String generateUserAgent(Context context) {
//        StringBuilder ua = new StringBuilder("api-client/");
//        ua.append(VERSION);
//
//        String packageName = context.getApplicationContext().getPackageName();
//
//        ua.append(" ");
//        ua.append(packageName);
//
//        PackageInfo pi = null;
//        try {
//            pi = context.getPackageManager().getPackageInfo(packageName, 0);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        if (pi != null) {
//            ua.append("/");
//            ua.append(pi.versionName);
//            ua.append("(");
//            ua.append(pi.versionCode);
//            ua.append(")");
//        }
//        ua.append(" Android/");
//        ua.append(Build.VERSION.SDK_INT);
//
//        try {
//            ua.append(" ");
//            ua.append(Build.PRODUCT);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try {
//            ua.append(" ");
//            ua.append(Build.MANUFACTURER);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try {
//            ua.append(" ");
//            ua.append(Build.MODEL);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return ua.toString();
//    }
}
