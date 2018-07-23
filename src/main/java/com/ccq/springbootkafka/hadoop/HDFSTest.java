package com.ccq.springbootkafka.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.net.URI;
import java.util.Enumeration;
import java.util.Properties;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/7/23 17:01
 ***@Version 1.0.0
 ********************************/
public class HDFSTest {
    public static void main(String[] args) throws Exception {
//      String uri = "hdfs://127.0.0.1:9000/";
        final FileSystem fs = HDFSUtils.init();
        HDFSUtils.createDir(fs, "/input/test2");
        HDFSUtils.delete(fs, "/input/test2.txt");

        //列出hdfs上 /input 目录下的所有文件
        FileStatus[] statuses = fs.listStatus(new Path("/input"));
        for (FileStatus status : statuses) {
            System.out.println(status);
        }

        HDFSUtils.writerString(fs, "chengchuanqiang", "/input/test.txt");
        HDFSUtils.readByLine(fs, "/input/test.txt");
    }

}
