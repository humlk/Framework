package com.sai.security.store.base;


public interface BaseStore {

    public void put(String key, Object value);

    public void putImmediate(String key, Object value);

    public <T> T get(String key);

    public void commit();
}
