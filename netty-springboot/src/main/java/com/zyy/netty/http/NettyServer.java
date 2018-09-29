package com.zyy.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @param null
 * @Description: 方法说明 netty http请求处理
 * @Author: zyy
 * @date: 2018/9/14 15:06
 */
public class NettyServer {

    private static final int port = 6789; //设置服务端端口
    private static EventLoopGroup group = new NioEventLoopGroup();   // 通过nio方式来接收连接和处理连接
    private static  ServerBootstrap b = new ServerBootstrap();

    public static void main(String[] args) {
        try {
            b.group(group);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new NettyServerFilter());
            // 服务器绑定端口监听
            ChannelFuture cf=b.bind(port).sync();
            System.out.println("服务端启动成功,端口是:"+port);
            cf.channel().closeFuture().sync();   // 监听服务器关闭监听
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully(); //关闭EventLoopGroup，释放掉所有资源包括创建的线程
        }


    }
}
