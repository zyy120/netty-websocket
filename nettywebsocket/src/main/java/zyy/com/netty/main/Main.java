package zyy.com.netty.main;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import zyy.com.netty.config.MyWebSocketChannelHandler;

public class Main {
    public static void main(String[] args) {
        EventLoopGroup boosGroup=new NioEventLoopGroup();
        EventLoopGroup workGroup=new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap=new ServerBootstrap();
            serverBootstrap.group(boosGroup,workGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.childHandler(new MyWebSocketChannelHandler());
            System.out.println("服务器开启等待客服端链接");
           Channel channel= serverBootstrap.bind(8888).sync().channel();
            channel.closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            boosGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }
}
