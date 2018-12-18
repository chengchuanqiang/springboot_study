package com.ccq.springbootkafka.hadoop;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.*;

/********************************
 *** HDFS操作工具类
 ***@Author chengchuanqiang
 ***@Date 2018/7/23 18:58
 ***@Version 1.0.0
 ********************************/
@Slf4j
public class HDFSUtils {

    private volatile static FileSystem hdfs;

    private HDFSUtils() {
    }


    /**
     * 获取hdfs的连接 双重校验锁+volatile解决并发问题
     *
     * @return FileSystem
     * @throws IOException 异常
     */
    public static FileSystem getHDFS() throws IOException {
        if (hdfs == null) {
            synchronized (HDFSUtils.class) {
                if (hdfs == null) {
                    // String uri = "hdfs://127.0.0.1:9000/";
                    Configuration conf = new Configuration();
                    // 设置 winutils.exe 的路径
                    System.setProperty("hadoop.home.dir", "D:\\bigdata\\hadoop-2.8.3");
                    hdfs = FileSystem.get(conf);
                }
            }
        }
        return hdfs;
    }

    /**
     * 初始化hdfs
     *
     * @throws IOException 异常
     */
    public static void init() throws IOException {
        HDFSUtils.getHDFS();
    }

    /**
     * 检查目录或文件是否存在
     *
     * @param filePath 文件或目录路径
     * @return 是否成功
     */
    public static boolean checkFileExist(String filePath) {
        try {
            Path f = new Path(filePath);
            return HDFSUtils.hdfs.exists(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 创建目录
     *
     * @param filePath 目录路径
     * @return 是否成功
     */
    public static boolean createDir(String filePath) {
        if (checkFileExist(filePath))
            return true;
        try {
            Path f = new Path(filePath);
            log.info("Create and Write :" + f.getName() + " to hdfs");
            return HDFSUtils.hdfs.mkdirs(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 创建文件
     *
     * @param filePath 文件路径
     * @return 是否成功
     */
    public static boolean createFile(String filePath) {
        try {
            Path f = new Path(filePath);
            FSDataOutputStream os = HDFSUtils.hdfs.create(f, true);
            os.close();
            return true;
        } catch (IllegalArgumentException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除文件或者目录
     *
     * @param filePath 文件路径
     * @throws Exception 异常
     */
    public static void deleteFile(String filePath) throws Exception {
        Path p = new Path(filePath);
        if (HDFSUtils.hdfs.isDirectory(p)) {
            HDFSUtils.hdfs.delete(p, true);
            log.info("删除文件夹成功: " + filePath);
        } else if (hdfs.isFile(p)) {
            HDFSUtils.hdfs.delete(p, false);
            log.info("删除文件成功: " + filePath);
        }
    }

    /**
     * 将text写入文件中
     *
     * @param text     文本
     * @param filePath 文件路径
     * @return 是否成功
     */
    public static boolean writerString(String text, String filePath) {
        try {
            Path p = new Path(filePath);
            if (hdfs.isFile(p)) {
                Path f = new Path(filePath);
                FSDataOutputStream os = HDFSUtils.hdfs.create(f, true);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "utf-8"));// 以UTF-8格式写入文件，不乱码
                writer.write(text);
                writer.close();
                os.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    /**
     * 读取文件中的内容
     *
     * @param filePath 文件路径
     * @return 问题内容
     */
    public static String readByLine(String filePath) {
        StringBuilder res = new StringBuilder();
        try {
            Path f = new Path(filePath);
            FSDataInputStream dis = HDFSUtils.hdfs.open(f);
            BufferedReader bf = new BufferedReader(new InputStreamReader(dis));// 防止中文乱码
            String line;
            while ((line = bf.readLine()) != null) {
                res.append(new String(line.getBytes(), "utf-8"));
            }
            dis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res.toString();
    }
}
