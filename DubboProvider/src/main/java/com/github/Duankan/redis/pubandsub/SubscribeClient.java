package com.github.Duankan.redis.pubandsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * @author duankang
 * @date 2019-06-08
 * @class redis消息订阅
 * @// FIXME: 2019/6/8  每天学习一点
 */
public class SubscribeClient {

    private Jedis jedis;
    private static final String EXIT_COMMAND = "exit";

    /**
     * @method 订阅客户端初始化连接
     * @desc
     */
    public SubscribeClient() {
        jedis = new Jedis("localhost");
    }

    /**
     * @param channel 订阅的频道
     * @method 订阅
     * @desc
     */
    public void subscribe(String... channel) {
        if (channel == null || channel.length <= 0) {
            return;
        }
        //消息处理,接收到消息时如何处理
        JedisPubSub jps = new JedisPubSub() {
            /**
             * @method 收到信息时回调
             * @desc JedisPubSub类是一个没有抽象方法的抽象类, 里面方法都是一些空实现
             *                  所以可以选择需要的方法覆盖,这儿使用的是SUBSCRIBE指令，所以覆盖了onMessage
             *                  如果使用PSUBSCRIBE指令，则覆盖onPMessage方法
             *                  当然也可以选择BinaryJedisPubSub,同样是抽象类，但方法参数为byte[]
             */
            @Override
            public void onMessage(String channel, String message) {
                if (Publisher.CHANNEL_KEY.equals(channel)) {
                    System.out.println("接收到消息: channel : " + message);
                    //接收到exit消息后退出
                    if (EXIT_COMMAND.equals(message)) {
                        System.exit(0);
                    }
                }
            }

            /**
             * @功能 订阅时回调函数
             */
            @Override
            public void onSubscribe(String channel, int subscribedChannels) {
                if (Publisher.CHANNEL_KEY.equals(channel)) {
                    System.out.println("订阅了频道:" + channel);
                }
            }
        };
        //可以订阅多个频道 当前线程会阻塞在这儿
        jedis.subscribe(jps, channel);
    }

    public static void main(String[] args) {
        SubscribeClient client = new SubscribeClient();
        client.subscribe(Publisher.CHANNEL_KEY);
        //并没有 unsubscribe方法
        //相应的也没有punsubscribe方法
    }
}
