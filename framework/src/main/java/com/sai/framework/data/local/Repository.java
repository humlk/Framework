package com.sai.framework.data.local;


import java.util.List;
import java.util.Map;

/**
 * 数据仓库，用来访问数据库或本地数据
 * @param <T>
 */
public interface Repository<T> {
    public void add(T t);
    public void remove(T t);
    public void update(T t);
    public List<T> query(Map condition);

}
