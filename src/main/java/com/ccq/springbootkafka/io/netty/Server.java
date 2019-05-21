package com.ccq.springbootkafka.io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/********************************
 *** Server 端
 ***@Author chengchuanqiang
 ***@Date 2019/1/28 11:39
 ***@Version 1.0.0
 ********************************/
public class Server {
    private static final int port = 2000;

    public static void main(String[] args) throws InterruptedException {
        /**
         * 配置服务端的nio线程组
         * EventLoopGroup是一个线程组，它包含了一组nio线程，专门用于网络事件的处理，实际上就是reactor线程组
         * 其中 group 是用于服务接受客户的连接
         * workGroup 是用于SocketChannel的网络读写
         */
        EventLoopGroup group = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(group, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoServerHandler());
                        }
                    });

            // 绑定端口，同步等待成功
            ChannelFuture future = serverBootstrap.bind(port).sync();
            System.out.println(Server.class.getName() + " start and listen on" + future.channel().localAddress());
            // 等待服务器监听端口关闭
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
            workGroup.shutdownGracefully().sync();
        }
    }
}