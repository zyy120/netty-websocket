package zyy.com.netty.config;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
*@Description: 方法说明 初始化各组件的类
*@Author: zyy
*@date: 2018/9/8
*/
public class MyWebSocketChannelHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel e) throws Exception {
        e.pipeline().addLast("http-codec",new HttpServerCodec());
        e.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
        e.pipeline().addLast("http-chunked",new ChunkedWriteHandler());
        e.pipeline().addLast("handler",new MyWebSocketChannelHandler());

    }
}
