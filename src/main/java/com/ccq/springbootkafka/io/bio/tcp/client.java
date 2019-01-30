package com.ccq.springbootkafka.io.bio.tcp;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/********************************
 *** 客户端
 ***@Author chengchuanqiang
 ***@Date 2019/1/30 10:17
 ***@Version 1.0.0
 ********************************/
public class client {

    private static final int port = 20001;

    public static void main(String[] args) {

        // 客户端socket
        Socket socket = null;
        try {
            socket = new Socket();

            // 连接服务端
            socket.connect(new InetSocketAddress(InetAddress.getLocalHost(), port), 3000);

            System.out.println("client ip: " + socket.getLocalAddress() + " port: " + socket.getLocalPort());
            System.out.println("server ip: " + socket.getInetAddress() + " port: " + socket.getPort());

            // 客户端处理
            ClientHandler clientHandler = new ClientHandler(socket);
            clientHandler.start();
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

        System.out.println("client is close");
    }

    /**
     * 客户端处理类
     */
    private static class ClientHandler {
        private Socket socket;
        private boolean finish = false;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void start() throws IOException {
            // 构造键盘输入流
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            do {

                String data = input.readLine();
                printWriter.println(data);
                printWriter.flush();
                System.out.println("client sender data: " + data);

                data = bufferedReader.readLine();
                if ("bye".equals(data)) {
                    finish = true;
                } else {
                    System.out.println("server return data: " + data);
                }
            } while (!finish);

            bufferedReader.close();
            printWriter.close();
            input.close();
        }
    }

}
