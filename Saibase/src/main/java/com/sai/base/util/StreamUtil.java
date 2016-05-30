package com.sai.base.util;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 
 * 系统名称： </br>
 * 模块名称： </br>
 * 功能说明： </br>
 * 
 * @ClassName:  FileUtil  
 * @author: huajie 
 * @version: 1.0
 * @date:   2014年12月31日 
 *
 */
public class StreamUtil {

    /** 读取缓冲区大小. */
    private static final int READBUF_SIZE = 1024;

    /**
     * 获取输入流的字节数组.
     *
     * @param is
     *            待读取的输入流
     * @return 输入流的字节数组
     * @throws IOException
     *             输入输出错误.
     */
    public static byte[] getInputStreamBytes(InputStream is) throws IOException {
        return getInputStreamBytes(is, false);
    }

    /**
     * 获取输入流的字节数组.
     *
     * @param is
     *            待读取的输入流
     * @param autoClose 是否自动关闭输入流
     * @return 输入流的字节数组
     * @throws IOException
     *             输入输出错误.
     */
    public static byte[] getInputStreamBytes(InputStream is, boolean autoClose) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            copy(is, baos, autoClose);
            return baos.toByteArray();
        } catch (IOException e) {
            throw e;
        } finally {
            close(baos);
        }
    }

    /**
     * 将输入流复制到输出流.
     *
     * @param is
     *            待复制的输入流
     * @param os
     *            目标输出流
     * @throws IOException
     *             输入输出错误.
     */
    public static void copy(InputStream is, OutputStream os) throws IOException {
        copy(is, os, false);
    }

    /**
     * 将输入流复制到输出流.
     *
     * @param is
     *            待复制的输入流
     * @param os
     *            目标输出流
     * @param autoClose 
     * 			  是否自动关闭流
     * @throws IOException
     *             输入输出错误.
     */
    public static void copy(InputStream is, OutputStream os, boolean autoClose) throws IOException {
        byte[] buf = new byte[READBUF_SIZE];
        int size;
        try {
            while ((size = is.read(buf)) != -1) {
                os.write(buf, 0, size);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (autoClose) {
                close(is);
                close(os);
            }
        }
    }

    /**
     * 关闭流.
     *
     * @param stream
     *            要关闭的流
     */
    public static void close(Closeable stream) {
        if (stream == null)
            return;
        try {
            stream.close();
        } catch (IOException e) {
        }
    }
}
