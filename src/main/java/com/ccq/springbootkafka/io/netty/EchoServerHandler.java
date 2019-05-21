package com.ccq.springbootkafka.io.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2019/1/28 11:40
 ***@Version 1.0.0
 ********************************/
public class EchoServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("Server received: " + msg.toString(CharsetUtil.UTF_8));
        ctx.write(Unpooled.copiedBuffer("Response from server , you have input \"" +
                msg.toString(CharsetUtil.UTF_8) + "\"!", CharsetUtil.UTF_8));
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
