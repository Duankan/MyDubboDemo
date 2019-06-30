package com.github.Duankan.rabbimq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * @author dunakang
 * @date 2019-06-30
 * @class ConnectionUtil rabbitMq连接工具类
 * @desc 每天学习一点（明天月薪12K）
 */

public class ConnectionUtil {

    Channel channel;//信道
    Connection connection;//连接
    String queueName;//队列名

    public ConnectionUtil(String queueName) throws IOException {
        this.queueName = queueName;
        // 创建连接工厂
        ConnectionFactory cf = new ConnectionFactory();
        // 设置rabbitmq服务器IP地址
        cf.setHost("127.0.0.1");
        // 设置rabbitmq服务器用户名
        cf.setUsername("guest");
        // 设置rabbitmq服务器密码
        cf.setPassword("guest");
        cf.setPort(AMQP.PROTOCOL.PORT);
        // 获取一个新的连接
        connection = cf.newConnection();
        // 创建一个通道
        channel = connection.createChannel();

        /**
         * @功能 申明一个队列，如果这个队列不存在，将会被创建
         * @param queue 队列名称
         * @param durable 持久性：true队列会再重启过后存在，但是其中的消息不会存在。
         * @param exclusive 是否只能由创建者使用
         * @param autoDelete 是否自动删除（没有连接自动删除）
         * @param arguments 队列的其他属性(构造参数)
         * @return 宣告队列的声明确认方法已成功声明。
         * @throws java.io.IOException if an error is encountered
         */
        channel.queueDeclare(queueName, true, false, false, null);
    }

    public void close() throws IOException {
        channel.close();
        connection.close();
    }
}