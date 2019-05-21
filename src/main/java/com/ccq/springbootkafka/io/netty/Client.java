package com.ccq.springbootkafka.io.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2019/1/28 13:47
 ***@Version 1.0.0
 ********************************/
public class Client {

    private static final String host = "127.0.0.1";
    private static final int port = 2000;

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoClientHandler());
                        }
                    });

            ChannelFuture future = bootstrap.connect().sync();
            if (future.channel().isActive()) {
                future.channel().writeAndFlush(Unpooled.copiedBuffer("Hello ccq!", CharsetUtil.UTF_8));
            }

            Thread.sleep(100);
        } finally {
            group.shutdownGracefully().sync();
        }

    }


}
