package com.sai.net.cache;

import java.util.Collections;
import java.util.Map;

/**
 * 缓存
 */
public interface Cache{

    public Entry get(String key);

    public void put(String key, Entry entry);

    /** 初始化 **/
    public void initialize();

    public void invalidate(String key, boolean fullExpire);

    public void remove(String key);

    public void clear();


    public static class Entry {
        /** 缓存数据 */
        public byte[] data;

        /** http头部中的缓存标签 */
        public String etag;

        /** 服务器返回数据的时间 */
        public long serverDate;

        /** 最后一次修改时间 */
        public long lastModified;

        /** time to live 过期时间 */
        public long ttl;

        /** 新鲜度时间 默认和ttl相同 */
        public long softTtl;

        /** Immutable response headers as received from server; must be non-null. */
        public Map<String, String> responseHeaders = Collections.emptyMap();

        /** 是否已过期 */
        public boolean isExpired() {
            return this.ttl < System.currentTimeMillis();
        }

        /** True if a refresh is needed from the original data source. */
        public boolean refreshNeeded() {
            return this.softTtl < System.currentTimeMillis();
        }
    }

}
