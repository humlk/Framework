package com.sai.framework.base.loader;

import com.sai.framework.presenter.BasePresenter;
import com.sai.framework.presenter.BaseView;

import java.util.HashMap;
import java.util.Map;


public class PresenterHolder{

    private HashMap<String, Object> mMap = new HashMap();

    public <P extends BasePresenter> P add(P p){
        mMap.put(p.getClass().getName(), p);
        return p;
    }

    public <P extends BasePresenter> P get(P p) {
        return (P)mMap.get(p.getClass().getName());
    }

    public <P extends BasePresenter> P load(Class cls) {
        String key = cls.getName();
        if(!mMap.containsKey(key)){
            mMap.put(key, (P)createObject(cls));
        }
        return (P)mMap.get(key);
    }

    private <P> P createObject(Class cls){
        try {
            return (P)cls.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void clear(){
        mMap.clear();
    }


//    public void create(){
//        for(Map.Entry<String,Object> entry : mMap.entrySet()){
//            if(entry.getValue() == null){
//                try {
//                    Object o = Class.forName(entry.getKey()).newInstance();
//                    mMap.put(o.getClass().getName(), o);
//                } catch (InstantiationException e) {
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    public void attach(BaseView view){
        for(Map.Entry<String,Object> entry : mMap.entrySet()){
            ((BasePresenter)entry.getValue()).onViewAttached(view);
        }
    }

    public void detach(){
        for(Map.Entry<String,Object> entry : mMap.entrySet()){
            ((BasePresenter)entry.getValue()).onViewDetached();
        }
    }
}
