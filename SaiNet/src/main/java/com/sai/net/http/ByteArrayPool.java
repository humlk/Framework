package com.sai.net.http;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 默认申请了两块内存空间mBuffersByLastUse和mBuffersBySize
 * 申请的空间使用完后就放入当前缓冲池.
 * 注意：缓存的空间是有脏数据的，所以申请的空间要写满。
 */
public class ByteArrayPool {

    /** 按使用事件排序，主要是当缓冲池超限时方便清理老的空间 */
    private List<byte[]> mBuffersByLastUse = new LinkedList<byte[]>();
    /** 按大小排序 **/
    private List<byte[]> mBuffersBySize = new ArrayList<byte[]>(64);

    /** 当前缓冲池的大小 */
    private int mCurrentSize = 0;

    /** 缓冲池的大小限制 **/
    private final int mSizeLimit;

    /** Compares buffers by size */
    protected static final Comparator<byte[]> BUF_COMPARATOR = new Comparator<byte[]>() {
        @Override
        public int compare(byte[] lhs, byte[] rhs) {
            return lhs.length - rhs.length;
        }
    };

    /**
     * @param sizeLimit the maximum size of the pool, in bytes
     */
    public ByteArrayPool(int sizeLimit) {
        mSizeLimit = sizeLimit;
    }

    /**
     * 从缓冲池中取出一片空间
     * @param len
     * @return
     */
    public synchronized byte[] getBuf(int len) {
        for (int i = 0; i < mBuffersBySize.size(); i++) {
            byte[] buf = mBuffersBySize.get(i);
            //从缓冲池中找到一份能够满足使用的空间
            if (buf.length >= len) {
                //修改缓冲池的大小
                mCurrentSize -= buf.length;
                //将当前缓冲的空间清除
                mBuffersBySize.remove(i);
                mBuffersByLastUse.remove(buf);
                return buf;
            }
        }
        //如果没找到就开辟一个空间
        return new byte[len];
    }

    /**
     * 放回缓冲池
     * @param buf
     */
    public synchronized void returnBuf(byte[] buf) {
        if (buf == null || buf.length > mSizeLimit) {
            return;
        }
        //当前使用的空间进行存储
        mBuffersByLastUse.add(buf);
        //mBuffersBySize是按大小排序的列表
        //使用二分法查找位置
        int pos = Collections.binarySearch(mBuffersBySize, buf, BUF_COMPARATOR);
        //没有找到，返回-1
        if (pos < 0) {
            pos = -pos - 1;
        }
        mBuffersBySize.add(pos, buf);
        mCurrentSize += buf.length;
        trim();
    }

    /**
     * 检查是否超出缓冲池的大小
     */
    private synchronized void trim() {
        while (mCurrentSize > mSizeLimit) {
            byte[] buf = mBuffersByLastUse.remove(0);
            mBuffersBySize.remove(buf);
            mCurrentSize -= buf.length;
        }
    }

}
