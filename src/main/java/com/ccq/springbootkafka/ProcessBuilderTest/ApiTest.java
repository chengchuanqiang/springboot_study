package com.ccq.springbootkafka.ProcessBuilderTest;

import lombok.extern.slf4j.Slf4j;

import javax.swing.filechooser.FileSystemView;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/********************************
 *** ProcessBuilder 使用
 ***@Author chengchuanqiang
 ***@Date 2019/5/27 15:35
 ***@Version 1.0.0
 ********************************/
@Slf4j
public class ApiTest {

    private static void openCalc() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("c:\\Windows\\System32\\calc.exe");
        try {
            log.info("启动子进程...");
            Process process = processBuilder.start();
//            new BufferedReader(new InputStreamReader(process.getInputStream())).lines().forEach(System.out::println);
            log.info("子进程启动成功...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void ping() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd", "/c", "ping www.baidu.com");
        File desktopFile = FileSystemView.getFileSystemView().getHomeDirectory();
        File file = new File(desktopFile, "output.txt");
        processBuilder = processBuilder.redirectOutput(file);
        try {
            Process process = processBuilder.start();
            log.info("子进程执行消息存放在：" + processBuilder.redirectOutput().file().getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
//        openCalc();
        ping();
    }

}
