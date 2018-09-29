package com.zyy.netty.reconnection.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
    private static final int port = 9876; //设置服务端端口
    private static EventLoopGroup boss = new NioEventLoopGroup();   // 通过nio方式来接收连接和处理连接
    private static  EventLoopGroup work = new NioEventLoopGroup();   // 通过nio方式来接收连接和处理连接
    private static ServerBootstrap b = new ServerBootstrap();

    public static void main(String[] args) {
        b.group(boss,work);
        b.channel(NioServerSocketChannel.class);
        b.childHandler(new NettyServerFilter());//设置过滤器

        //服务器断开监听
        try {
            ChannelFuture cf=b.bind(port).sync();
            System.out.println("服务端启动成功,端口是:"+port);
            //监听服务器关闭
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //关闭EventLoopGroup，释放掉所有资源包括创建的线程
            work.shutdownGracefully();
            boss.shutdownGracefully();
        }

    }
}
