package com.sai.security.store.base;


import org.json.JSONException;
import org.json.JSONObject;

public abstract class JsonStore implements BaseStore{

    private JSONObject mJSONObject = null;

    protected void create(){
        mJSONObject = new JSONObject();
    }
    protected void create(String defString){
        try {
            mJSONObject = new JSONObject(defString);
        } catch (JSONException e) {
            mJSONObject = new JSONObject();
        }
    }

    protected String getData(){
        if(mJSONObject == null){
            return "";
        }
        return mJSONObject.toString();
    }
    @Override
    public synchronized void put(String key, Object value) {
        try {
            mJSONObject.put(key, value);
        } catch (JSONException e) {

        }
    }

    @Override
    public synchronized void putImmediate(String key, Object value) {
        put(key, value);
        commit();
    }

    @Override
    public synchronized <T> T get(String key) {
        return (T)mJSONObject.opt(key);
    }
}
