package com.zyy.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;

public class NettyServerFilter extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline channelPipeline=socketChannel.pipeline();
        channelPipeline.addLast("encoder",new HttpRequestEncoder())
                .addLast("decoder",new HttpRequestDecoder())
                .addLast("aggregator",new HttpObjectAggregator(10*1024*1024));
        channelPipeline.addLast("handler",new NettyServerHandler());
    }
}
