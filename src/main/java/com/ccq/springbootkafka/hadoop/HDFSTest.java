package com.ccq.springbootkafka.hadoop;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;

/********************************
 *** 测试HDFS工具类
 ***@Author chengchuanqiang
 ***@Date 2018/7/23 17:01
 ***@Version 1.0.0
 ********************************/
public class HDFSTest {
    public static void main(String[] args) throws Exception {
        HDFSUtils.init();

        HDFSUtils.createDir("/input/test2");
        HDFSUtils.createDir("/input/test");
        HDFSUtils.deleteFile("/input/test2");

        HDFSUtils.createFile("/input/test.txt");
        HDFSUtils.writerString("chengchuanqiang", "/input/test.txt");
        HDFSUtils.readByLine("/input/test.txt");

        //列出hdfs上 /input 目录下的所有文件
        FileStatus[] statuses = HDFSUtils.getHDFS().listStatus(new Path("/input"));
        for (FileStatus status : statuses) {
            System.out.println(status);
        }

    }

}
