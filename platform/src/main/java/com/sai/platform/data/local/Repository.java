package com.sai.platform.data.local;


import java.util.Map;

public interface Repository<T> {
    public void add(T t);
    public void remove(T t);
    public void update(T t);
    public void query(Map condition);

}
