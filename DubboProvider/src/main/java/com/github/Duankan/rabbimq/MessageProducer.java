package com.github.Duankan.rabbimq;

import com.github.Duankan.utils.SerializerUtil;
import com.rabbitmq.client.MessageProperties;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author duankang
 * @date 2019-06-30
 * @class MessageProducer 消息生产者
 * @desc 每天学习一点（明年月薪12k）
 */
public class MessageProducer {

    private ConnectionUtil connectionUtil;

    public MessageProducer(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    /**
     * @param object 序列化对象
     * @throws IOException
     * @function 发送消息
     */
    public void sendMessage(Serializable object) throws IOException {
        /**
         * Publish a message
         * @param exchange 消息交换机名称,空字符串将使用直接交换器模式，发送到默认的Exchange=amq.direct
         * @param routingKey 路由关键字
         * @param mandatory 监听是否有符合的队列
         * @param BasicProperties 设置消息持久化：MessageProperties.PERSISTENT_TEXT_PLAIN是持久化；MessageProperties.TEXT_PLAIN是非持久化
         * @param body 消息对象
         * @throws java.io.IOException if an error is encountered
         */
        connectionUtil.channel.basicPublish(
                "",
                connectionUtil.queueName,
                true,
                MessageProperties.TEXT_PLAIN,
                SerializationUtils.serialize(object));
        System.out.println("MessageProducer发送了一条消息：" + object);
    }
}
