package com.zyy.netty.reconnection.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

import java.util.Date;

public class NettyServerHander extends ChannelInboundHandlerAdapter{

    /** 客户端请求的心跳命令  */
    private static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("hb_request",
            CharsetUtil.UTF_8));

    /** 空闲次数 */
    private int idle_count = 1;

    /** 发送次数 */
    private int count = 1;

    /**循环次数 */
    private int fcount = 1;


    /**
     * @param
     * @Description: 方法说明 建立连接时
     * @Author: zyy
     * @date: 2018/9/14 16:46
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("建立连接时："+new Date());
        ctx.fireChannelActive();
    }

    /**
     * @param
     * @Description: 方法说明 关闭连接时
     * @Author: zyy
     * @date: 2018/9/14 16:46
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("关闭连接时："+new Date());
        final EventLoop eventLoop=ctx.channel().eventLoop();
        NettyClient.nettyClient.doConnect(new Bootstrap(), eventLoop);
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("第"+count+"次"+",客户端接受的消息:"+msg);
        count++;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("循环请求的时间："+new Date()+"，次数"+fcount);
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (IdleState.WRITER_IDLE.equals(event.state())) {  //如果写通道处于空闲状态,就发送心跳命令
                if(idle_count <= 2){   //设置发送次数
                    idle_count++;
                    ctx.channel().writeAndFlush(HEARTBEAT_SEQUENCE.duplicate());
                }else{
                    System.out.println("不再发送心跳请求了!");
                }
                fcount++;
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
