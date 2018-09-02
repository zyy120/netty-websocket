package zyy.com.netty.config;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

import java.util.Date;

/**
 * describe: 接受处理websocket 请求核心业务处理类
 *
 * @author zyy
 * @date 2018/09/02
 */
public class WebSocketHander extends SimpleChannelInboundHandler {

    private WebSocketServerHandshaker handshaker;
    private  static  final String  WEB_SCOKET_URL = "ws://127.0.0.1:8888/websocket";

    //客服端与服务端通信核心方法
    @Override
    protected void messageReceived(ChannelHandlerContext context, Object msg) throws Exception {

        if (msg instanceof FullHttpRequest ) {//处理http 请求对下
            handHttpRequset(context,(FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame ) { //处理websocket 连接业务对象
            handWebsocketFrame(context,(WebSocketFrame)msg);
        }
    }

    /**
     * TODO: 方法描述 处理客服端与服务器之前的websocket 业务
     * @author zyy
     * @date 2018/9/2
     * @param
     * @return
     * @throws
     */
    private  void  handWebsocketFrame(ChannelHandlerContext ctx,WebSocketFrame frame){

        if(frame instanceof CloseWebSocketFrame ){
            handshaker.close(ctx.channel(),((CloseWebSocketFrame) frame).retain());
        }
        //判断是否是ping消息
        if (frame instanceof PingWebSocketFrame ){
            ctx.channel().write(new PongWebSocketFrame( frame.content().retain()));
        }

        if (! (frame instanceof  TextWebSocketFrame)) {
            System.out.println("目前不支持二进制消息");
            throw  new RuntimeException("【"+this.getClass().getName()+"】不支持消息");
        }

        String request=((TextWebSocketFrame) frame).text();
        System.out.println("服务端接受到客服端的消息-->"+request);
        TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(new Date().toString()+ctx.channel().id()+"-->"+request);

        //群发消息到每一个服务器

        NettyConfig.group.writeAndFlush(textWebSocketFrame);
    }
    /**
     * TODO: 方法描述
     * @author zyy
     * @date 2018/9/2
     * @param  
     * @return 
     * @throws 
     */
    private  void handHttpRequset (ChannelHandlerContext ctx,FullHttpRequest req) {

        if(! req.getDecoderResult().isSuccess() || !("websocket").equals(req.headers().get("Upgrade"))) {
            sendHttpResponse(ctx,req,new DefaultFullHttpResponse(HttpVersion .HTTP_1_1,HttpResponseStatus.BAD_REQUEST));
            return;
        }

        WebSocketServerHandshakerFactory wsFactory =new WebSocketServerHandshakerFactory(WEB_SCOKET_URL,null,false);

        handshaker= wsFactory.newHandshaker(req);

        if ( handshaker == null ) {
            WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
        }else {
            handshaker.handshake(ctx.channel(),req);
        }
    }

    private void sendHttpResponse (ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {

        if (res.getStatus().code() != 200 ) {
            ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(),CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        ChannelFuture future = ctx.channel().writeAndFlush(res);
        if (res.getStatus().code() !=200 ) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }
    //客服端与服务端连接调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        NettyConfig.group.add(ctx.channel());
        System.out.println("客服端与服务端连接启动 start::");
    }

    //客服端与服务端断开连接调用
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        NettyConfig.group.remove(ctx.channel());
        System.out.println("客服端与服务端连接关闭 end t::");
    }

    //客服端与服务端发送数据过来后断开连接调用
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    //工程异常处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
