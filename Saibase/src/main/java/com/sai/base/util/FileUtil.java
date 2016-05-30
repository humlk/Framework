package com.sai.base.util;

import com.sai.base.exception.BaseException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


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
public class FileUtil {

    /**
     * 获取文件内容.
     *
     * @param filePath
     *            文件路径
     * @return 文件的字节数组
     * @throws BaseException
     *             输入输出错误.
     */
    public static byte[] getFileContent(String filePath) throws IOException, BaseException {
        if (filePath == null) {
            throw new BaseException("file not exist");
        }
        InputStream is = null;
        try {
            is = getFileStream(filePath);
            if (is == null) {
                return null;
            }
            byte[] data = StreamUtil.getInputStreamBytes(is);
            return data;
        } catch (IOException e) {
            throw e;
        } finally {
            StreamUtil.close(is);
        }
    }
    public static byte[] getFileContent(File file) throws IOException, BaseException {
        if (file == null) {
            throw new BaseException("file not exist");
        }
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            if (is == null) {
                return null;
            }
            byte[] data = StreamUtil.getInputStreamBytes(is);
            return data;
        } catch (IOException e) {
            throw e;
        } finally {
            StreamUtil.close(is);
        }
    }
    /**
     * 获取文件流.
     *
     * @param filePath
     *            文件路径
     * @return 文件流
     */
    public static InputStream getFileStream(String filePath) {
        if (filePath == null) {
            return null;
        }
        try {
            if (filePath.startsWith("file:///android_asset/")) {
                return Thread.currentThread().getContextClassLoader().getResourceAsStream("assets/" + filePath.substring(22));
            } else {
                File file = new File(filePath);
                if (!file.exists() || !file.isFile()) {
                    return null;
                }
                return new FileInputStream(filePath);
            }
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 将字符串保存为文件(覆盖模式).
     *
     * @param filePath
     *            保存路径
     * @param data
     *            待保存的字符串
     */
    public static void saveToFileByBytes(String filePath, byte[] data) throws BaseException{
        saveToFileByBytes(filePath, data, false);
    }

    /**
     * 将字符串保存为文件.
     *
     * @param filePath
     *            保存路径
     * @param data
     *            待保存的字符串
     * @param append
     *            是否使用追加模式保存文件
     * @throws BaseException
     *             输入输出错误.
     */
    public static void saveToFileByBytes(String filePath, byte[] data, boolean append) throws BaseException {
        if (filePath == null || data == null) {
            throw new BaseException("file not exist");
        }
        saveToFileByStream(filePath, new ByteArrayInputStream(data), append);
    }

    /**
     * 将字符串保存为文件(覆盖模式).
     *
     * @param filePath
     *            保存路径
     * @param src
     *            待保存的字符串
     * @throws BaseException
     *             输入输出错误.
     * @throws UnsupportedEncodingException 
     *             编码不支持
     */
    public static void saveToFileByString(String filePath, String src) throws BaseException, UnsupportedEncodingException {
        saveToFileByString(filePath, src, false, null);
    }

    /**
     * 将字符串保存为文件(覆盖模式).
     *
     * @param filePath
     *            保存路径
     * @param src
     *            待保存的字符串
     * @param encoding 
     *             字符编码
     * @throws BaseException
     *             输入输出错误.
     * @throws UnsupportedEncodingException 
     *             编码不支持
     */
    public static void saveToFileByString(String filePath, String src, String encoding) throws BaseException, UnsupportedEncodingException {
        saveToFileByString(filePath, src, false, encoding);
    }

    /**
     * 将字符串保存为文件.
     *
     * @param filePath
     *            保存路径
     * @param data
     *            待保存的字符串
     * @param encoding 
     *            保存编码
     * @param append
     *            是否使用追加模式保存文件
     * @throws BaseException
     *             输入输出错误.
     * @throws UnsupportedEncodingException
     * 			   编码不支持 
     */
    public static void saveToFileByString(String filePath, String data, boolean append, String encoding) throws BaseException,
            UnsupportedEncodingException {
        if (filePath == null || data == null) {
            throw new BaseException("file not exist");
        }
        if (encoding == null) {
            saveToFileByStream(filePath, new ByteArrayInputStream(data.getBytes()), append);
        } else {
            saveToFileByStream(filePath, new ByteArrayInputStream(data.getBytes(encoding)), append);
        }

    }

    /**
     * 将输入流保存为文件(覆盖模式).
     *
     * @param filePath
     *            保存路径
     * @param is
     *            待保存的输入流
     * @throws BaseException
     *             输入输出错误.
     */
    public static void saveToFileByStream(String filePath, InputStream is) throws BaseException {
        saveToFileByStream(filePath, is, false);
    }

    /**
     * 将输入流保存为文件.
     *
     * @param filePath
     *            保存路径
     * @param is
     *            待保存的输入流
     * @param append
     *            是否使用追加模式保存文件
     * @throws BaseException
     *             输入输出错误.
     */
    public static void saveToFileByStream(String filePath, InputStream is, boolean append) throws BaseException {
        if (filePath == null || is == null) {
            throw new BaseException("file not exist");
        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath);
            StreamUtil.copy(is, fos);
        } catch (IOException e) {
            throw new BaseException(e, filePath);
        } finally {
            StreamUtil.close(fos);
        }
    }

    /**
     * 解压缩文件到指定的目录.
     *
     * @param zipFilePath
     *            需要解压缩的文件
     * @param destPath
     *            解压缩后存放的路径
     * @throws BaseException
     *             输入输出错误.
     */
    public static void unZip(String zipFilePath, String destPath) throws BaseException {
        if (zipFilePath == null || destPath == null) {
            throw new BaseException("file not exist");
        }

        if (!destPath.endsWith("/")) {
            destPath = destPath + "/";
        }
        FileOutputStream fos = null;
        ZipInputStream zis = null;
        ZipEntry zipEntry = null;
        File file = null;
        int readedBytes = 0;
        byte buf[] = new byte[4096];
        try {
            zis = new ZipInputStream(new BufferedInputStream(getFileStream(zipFilePath)));
            while ((zipEntry = zis.getNextEntry()) != null) {
                file = new File(destPath + zipEntry.getName());
                if (zipEntry.isDirectory()) {
                    file.mkdirs();
                } else {
                    // 如果指定文件的目录不存在,则创建之.
                    File parent = file.getParentFile();
                    if (!parent.exists()) {
                        parent.mkdirs();
                    }
                    fos = new FileOutputStream(file);
                    while ((readedBytes = zis.read(buf)) > 0) {
                        fos.write(buf, 0, readedBytes);
                    }
                    fos.close();
                }
                zis.closeEntry();
            }
        } catch (IOException e) {
            throw new BaseException(e, "file read or write error");
        } finally {
            StreamUtil.close(zis);
        }
    }

    /**
     * 将文件或文件夹进行Zip压缩.
     *
     * @param srcPath
     *            待压缩的文件或文件夹路径
     * @param zipFilePath
     *            压缩后文件的保存路径
     * @throws BaseException
     *             输入输出错误.
     */
    public static void zip(String srcPath, String zipFilePath) throws BaseException {
        zip(zipFilePath, new File(srcPath));
    }

    /**
     * 将文件或文件夹进行Zip压缩.
     *
     * @param zipFilePath
     *            压缩后文件的保存路径
     * @param srcFile
     *            待压缩的文件或文件夹对象
     * @throws BaseException
     *             输入输出错误.
     */
    public static void zip(String zipFilePath, File srcFile) throws BaseException {
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(new FileOutputStream(zipFilePath));
            zip(zos, srcFile, "");
        } catch (IOException e) {
            throw new BaseException(e, "file read or write error");
        } finally {
            StreamUtil.close(zos);
        }
    }

    /**
     * 将文件或文件夹进行Zip压缩.
     *
     * @param zos
     *            Zip输出流
     * @param srcFile
     *            待压缩的文件或文件夹对象
     * @param baseDir
     *            Zip内的文件基地址
     * @throws IOException
     *             输入输出错误.
     */
    private static void zip(ZipOutputStream zos, File srcFile, String baseDir) throws IOException {
        if (srcFile.isDirectory()) {
            File[] fileList = srcFile.listFiles();
            zos.putNextEntry(new ZipEntry(baseDir + "/"));
            baseDir = baseDir.length() == 0 ? "" : baseDir + "/";
            for (int i = 0; i < fileList.length; i++) {
                zip(zos, fileList[i], baseDir + fileList[i].getName());
            }
        } else {
            zos.putNextEntry(new ZipEntry(baseDir));
            FileInputStream in = new FileInputStream(srcFile);
            int b;
            while ((b = in.read()) != -1) {
                zos.write(b);
            }
            in.close();
        }
    }

    /**
     * 删除文件或文件夹.
     *
     * @param fileName
     *            要删除的文件或文件夹名
     * @return 删除成功返回真，否则返回假
     */
    public static boolean deleteFile(String fileName) {
        return deleteFile(new File(fileName));
    }

    /**
     * 删除文件或文件夹.
     *
     * @param file
     *            要删除的文件对象
     * @return 删除成功返回真，否则返回假
     */
    public static boolean deleteFile(File file) {
        if (file == null || !file.exists()) {
            return true;
        } else if (file.isDirectory()) {
            File[] subFiles = file.listFiles();
            if (subFiles != null) {
                for (File subFile : subFiles) {
                    if (!deleteFile(subFile)) {
                        return false;
                    }
                }
            }
        }
        return file.delete();
    }

    /**
     * 拷贝文件.
     * @param sourceFile 源文件
     * @param targetFile 目标文件
     * @throws IOException 输入输出错误
     */
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        // 新建文件输入流并对它进行缓冲
        FileInputStream input = new FileInputStream(sourceFile);
        BufferedInputStream inBuff = new BufferedInputStream(input);

        // 新建文件输出流并对它进行缓冲
        FileOutputStream output = new FileOutputStream(targetFile);
        BufferedOutputStream outBuff = new BufferedOutputStream(output);

        // 缓冲数组
        byte[] b = new byte[1024 * 5];
        int len;
        while ((len = inBuff.read(b)) != -1) {
            outBuff.write(b, 0, len);
        }
        // 刷新此缓冲的输出流
        outBuff.flush();

        // 关闭流
        inBuff.close();
        outBuff.close();
        output.close();
        input.close();
    }

    /**
     * 拷贝目录，懒惰模式，遇到错误就会停止拷贝.
     * @param sourceDir 源目录
     * @param targetDir 目标目录
     * @throws IOException 输入输出错误
     */
    public static void copyDirectiory(String sourceDir, String targetDir) throws IOException {
        copyDirectiory(sourceDir, targetDir, true);
    }

    /**
     * 拷贝目录.
     * @param sourceDir 源目录
     * @param targetDir 目标目录
     * @param lazy 懒惰模式开关，懒惰模式时遇到错误就会停止拷贝
     * @throws IOException 输入输出错误
     */
    public static void copyDirectiory(String sourceDir, String targetDir, boolean lazy) throws IOException {
        // 新建目标目录
        (new File(targetDir)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(sourceDir)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // 源文件
                File sourceFile = file[i];
                // 目标文件
                File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + file[i].getName());
                try {
                    copyFile(sourceFile, targetFile);
                } catch (IOException e) {
                    if (lazy)
                        throw e;
                }
            }
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹
                String dir1 = sourceDir + File.separator + file[i].getName();
                // 准备复制的目标文件夹
                String dir2 = targetDir + File.separator + file[i].getName();
                copyDirectiory(dir1, dir2, lazy);
            }
        }
    }

    /**
     * 获取平台统一文件夹路径
     * @param path 
     * @return 统一路径
     */
    public static String getUniformDirPath(String path) {
        if (path == null || "".endsWith(path)) {
            return "";
        }
        path = getUniformPath(path);
        if (!path.endsWith(File.separator)) {
            path += File.separator;
        }
        return path;
    }

    /**
     * 获取平台统一路径
     * @param path 
     * @return 统一路径
     */
    public static String getUniformPath(String path) {
        if (File.separator.equals("/")) {
            path = path.replace("\\", File.separator);
        } else {
            path = path.replace("/", File.separator);
        }
        return path;
    }

    /**
     * 获取文件扩展名
     * @param filePath 文件名
     * @return 文件扩展名
     */
    public static String getFileExtension(String filePath) {
        filePath = getUniformPath(filePath);
        int index = filePath.lastIndexOf(File.separator);
        if (index != -1) {
            filePath = filePath.substring(index + 1);
        }
        index = filePath.lastIndexOf(".");
        if (index != -1) {
            return filePath.substring(index + 1);
        } else {
            return "";
        }
    }
    
    public static void makeDir(String filePath) {
        if (filePath != null && filePath.length() > 0) {
            File file = new File(filePath);
            if (!file.isDirectory()) {
                if (filePath.lastIndexOf("/") != -1) {
                    filePath = filePath.substring(0, filePath.lastIndexOf("/"));
                }
            }
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
    }
}
