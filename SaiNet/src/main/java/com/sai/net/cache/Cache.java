package com.sai.net.cache;

import java.util.Collections;
import java.util.Map;

/**
 * 缓存
 */
public interface Cache extends CacheInterface{

    public Entry get(String key);

    public void put(String key, Entry entry);

    /** 初始化 **/
    public void initialize();

    public void invalidate(String key, boolean fullExpire);

    public void remove(String key);

    public void clear();


    public static class Entry {
        /** The data returned from cache. */
        public byte[] data;

        /** ETag for cache coherency. */
        public String etag;

        /** Date of this response as reported by the server. */
        public long serverDate;

        /** 最后一次修改时间 */
        public long lastModified;

        /** TTL for this record. */
        public long ttl;

        /** Soft TTL for this record. */
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
