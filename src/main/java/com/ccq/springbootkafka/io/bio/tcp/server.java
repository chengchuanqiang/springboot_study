package com.ccq.springbootkafka.io.bio.tcp;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/********************************
 *** 服务端
 ***@Author chengchuanqiang
 ***@Date 2019/1/30 10:16
 ***@Version 1.0.0
 ********************************/
public class server {

    /**
     * 服务端开启端口号
     */
    private static final int port = 20001;
    /**
     * 固定大小线程池，个数为CPU处理核数 * 2
     */
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    public static void main(String[] args) throws IOException {

        // System.out.println(Runtime.getRuntime().availableProcessors());

        // 创建服务端socket
        ServerSocket serverSocket = new ServerSocket();
        // 绑定端口
        serverSocket.bind(new InetSocketAddress(port));
        System.out.println("server start success, ip: " + serverSocket.getLocalSocketAddress() + " port: " + serverSocket.getLocalPort());

        while (true) {
            // 等待客户端连接，阻塞
            Socket clientSocket = serverSocket.accept();

            // 单独启动一个线程去处理客户端连接，适当的增大并发量
            ServerHandler serverHandler = new ServerHandler(clientSocket);
            threadPool.submit(serverHandler);
        }
    }

    /**
     * 服务端处理类
     */
    private static class ServerHandler implements Runnable {

        private Socket socket;
        private boolean finish = false;

        public ServerHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("client connect ip: " + socket.getInetAddress() + " port: " + socket.getPort());
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());

                do {
                    String data = bufferedReader.readLine();
                    if ("bye".equals(data)) {
                        finish = true;
                        printWriter.println("bye");
                    } else {
                        System.out.println("receive data: " + data);
                        printWriter.println("len: " + data.length());
                    }
                    printWriter.flush();
                } while (!finish);

                bufferedReader.close();
                printWriter.close();
            } catch (IOException e) {
                System.out.println("connect exception ");
                e.printStackTrace();
            } finally {
                try {
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("client disconnect: " + socket.getInetAddress() + " port: " + socket.getPort());
        }
    }
}
