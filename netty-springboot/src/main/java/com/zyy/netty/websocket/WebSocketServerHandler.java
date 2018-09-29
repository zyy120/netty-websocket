package com.zyy.netty.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

/**
 * @param null
 * @Description: 方法说明 执行流程  handlerAdded ->channelRead0->handlerRemoved
 * @Author: zyy
 * @date: 2018/9/18 14:01
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    @Override
    protected void channelRead0(ChannelHandlerContext cxt, TextWebSocketFrame msg) throws Exception {

        Channel channel=cxt.channel();
        System.out.println(channel.remoteAddress()+"::"+msg.text());
        System.out.println("llllll");
        cxt.channel().writeAndFlush(new TextWebSocketFrame("来自服务器端："+LocalDateTime.now()));
    }

    /**
     * @param null
     * @Description: 方法说明
     * @Author: zyy
     * @date: 2018/9/18 14:00
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ChannelId" + ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("用户下线: " + ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
    }
}
