package com.github.Duankan.rabbimq;

import com.rabbitmq.client.ConfirmListener;

import java.io.IOException;

/**
 * @author duankang
 * @date 2019-06-30
 * @class MyConfirmListener 生产者确认模式监听器
 * @desc 每天学习一点（明年月薪12k）
 */
public class MyConfirmListener implements ConfirmListener {

    /**
     * @param deliveryTag 消息Id
     * @param multiple
     * @function 生产者发送消息到exchange成功回调
     * @desc 1.消息被Exchange接受以后，如果没有匹配的Queue，则会被丢弃。是可以设置ReturnListener监听来监听有没有匹配的队列。
     * 2.消息被exchange接收过后，还需要通过一定的匹配规则分发到对应的队列queue中。
     */
    public void handleAck(long deliveryTag, boolean multiple) throws IOException {
        //注意：deliveryTag是broker给消息指定的唯一id（从1开始）
        System.out.println("Exchange接收消息：" + deliveryTag + "（deliveryTag）成功！multiple=" + multiple);
    }

    /**
     * @param deliveryTag 消息id
     * @param multiple
     * @function 生产者发送消息到exchange失败回调
     * @desc 1.失败后，服务器丢失了此消息。
     * 2.意，丢失的消息仍然可以传递给消费者，但broker不能保证这一点。
     */
    public void handleNack(long deliveryTag, boolean multiple) throws IOException {
        System.out.println("Exchange接收消息：" + deliveryTag + "（deliveryTag）失败！服务器broker丢失了消息");
    }
}