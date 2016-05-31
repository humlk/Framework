//package com.sai.net.manager.life;
//
//
//import android.content.Context;
//
//import com.sai.net.request.RequestBuilder;
//
//import java.lang.ref.WeakReference;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class LifeManager {
//    private static LifeManager instance = new LifeManager();
//
//    private Map<String, WeakReference<Context>> contextTagMap = new HashMap<>();
//    private Map<String, List<RequestBuilder>> contextRequestMap = new HashMap<>();
//
//
//    public static LifeManager get(){
//        return instance;
//    }
//
//
//    public RequestBuilder getRequest(){
//
//        return new RequestBuilder();
//
//    }
//}
