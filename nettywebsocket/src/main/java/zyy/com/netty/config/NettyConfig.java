package zyy.com.netty.config;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * describe: netty 基本配置信息
 *
 * @author zyy
 * @date 2018/09/02
 */
public class NettyConfig {

    public  static ChannelGroup group =new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

}
