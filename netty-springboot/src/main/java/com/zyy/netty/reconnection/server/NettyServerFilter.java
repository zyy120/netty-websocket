package com.zyy.netty.reconnection.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @param
 * @Description: 方法说明 netty 断线重新连接心跳
 * @Author: zyy
 * @date: 2018/9/14 15:59
 */
public class NettyServerFilter extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline channelPipeline= socketChannel.pipeline();
        // 以("\n")为结尾分割的 解码器
//        ph.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        channelPipeline.addLast(new IdleStateHandler(5,0,0,TimeUnit.SECONDS))
                .addLast("decoder",new StringDecoder())
                .addLast("encoder",new StringEncoder())
                .addLast("hander",new NettyServerHander());

    }
}
