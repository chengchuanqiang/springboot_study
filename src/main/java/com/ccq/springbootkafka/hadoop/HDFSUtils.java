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

    public static FileSystem init() throws IOException {
        Configuration conf = new Configuration();
        // 设置 winutils.exe 的路径
        System.setProperty("hadoop.home.dir", "D:\\bigdata\\hadoop-2.8.3");
        return FileSystem.get(conf);
    }

    public static boolean checkFileExist(FileSystem hdfs, String filename) {
        try {
            Path f = new Path(filename);
            return hdfs.exists(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean createDir(FileSystem hdfs, String dirName) {
        if (checkFileExist(hdfs, dirName))
            return true;
        try {
            Path f = new Path(dirName);
            log.info("Create and Write :" + f.getName() + " to hdfs");
            return hdfs.mkdirs(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean mkfile(FileSystem hdfs, String filePath) {
        try {
            Path f = new Path(filePath);
            FSDataOutputStream os = hdfs.create(f, true);
            os.close();
            return true;
        } catch (IllegalArgumentException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void delete(FileSystem hdfs, String src) throws Exception {
        Path p1 = new Path(src);
        if (hdfs.isDirectory(p1)) {
            hdfs.delete(p1, true);
            log.info("删除文件夹成功: " + src);
        } else if (hdfs.isFile(p1)) {
            hdfs.delete(p1, false);
            log.info("删除文件成功: " + src);
        }
    }

    public static void writerString(FileSystem hdfs, String text, String path) {

        try {
            Path f = new Path(path);
            FSDataOutputStream os = hdfs.create(f, true);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "utf-8"));// 以UTF-8格式写入文件，不乱码
            writer.write(text);
            writer.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public static boolean readByLine(FileSystem hdfs, String hdfsFilename) {
        try {
            Path f = new Path(hdfsFilename);

            FSDataInputStream dis = hdfs.open(f);

            BufferedReader bf = new BufferedReader(new InputStreamReader(dis));// 防止中文乱码
            String line = null;
            while ((line = bf.readLine()) != null) {
                log.info(new String(line.getBytes(), "utf-8"));
            }

            dis.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
