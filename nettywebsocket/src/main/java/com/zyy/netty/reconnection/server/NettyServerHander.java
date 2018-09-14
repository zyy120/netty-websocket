package com.zyy.netty.reconnection.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class NettyServerHander extends ChannelInboundHandlerAdapter {

    /** 空闲次数 */
    private int idle_count =1;
    /** 发送次数 */
    private int count = 1;


    /**
     * @param
     * @Description: 方法说明 业务逻辑处理
     * @Author: zyy
     * @date: 2018/9/14 15:42
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("第"+count+"次"+",服务端接受的消息:"+msg);
        String message = (String) msg;
        if("hb_request".equals("message")) {//如果是心跳命令，则发送给客户端;否则什么都不做
            ctx.write("服务端成功收到心跳信息");
            ctx.flush();
        }
        count++;
    }

    /**
     * @param
     * @Description: 方法说明  超时处理 如果5秒没有接受到客服端的心跳，
     * 就触发 如果超过两次则直接关闭
     *
     * @Author: zyy
     * @date: 2018/9/14 15:44
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event=(IdleStateEvent)evt;
            if (IdleState.READER_IDLE.equals(event.state())) { //如果读通道处于空闲状态，说明没有接收到心跳命令
                System.out.println("已经5秒没有接收到客户端的信息了");
                if(idle_count >1){
                    System.out.println("关闭这个不活跃的channel");
                    ctx.channel().close();
                }
            }

        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    /**
     * @param
     * @Description: 方法说明 异常处理
     * @Author: zyy
     * @date: 2018/9/14 15:41
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
