/**
 * MIT License
 * Copyright (c) 2018 yadong.zhang
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.study.shiro;

import com.study.shiro.netty.constant.WebSocketConstant;
import com.study.shiro.netty.handler.WebSocketChannelHandler;
import com.study.shiro.netty.service.WebSocketInfoService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 程序启动类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@SpringBootApplication
public class ShiroAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroAdminApplication.class, args);

        /*netty 启动*/

        //两个线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workGroup);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new WebSocketChannelHandler());
            System.out.println("服务端开启等待客户端连接....");
            Channel ch = b.bind(WebSocketConstant.WEB_SOCKET_PORT).sync().channel();

            //创建一个定长线程池，支持定时及周期性任务执行
            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
            WebSocketInfoService webSocketInfoService = new WebSocketInfoService();
            //定时任务:扫描所有的Channel，关闭失效的Channel
            executorService.scheduleAtFixedRate(webSocketInfoService::scanNotActiveChannel,
                    3, 60, TimeUnit.SECONDS);

            //定时任务:向所有客户端发送Ping消息
            executorService.scheduleAtFixedRate(webSocketInfoService::sendPing,
                    3, 50, TimeUnit.SECONDS);

            ch.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //退出程序
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
