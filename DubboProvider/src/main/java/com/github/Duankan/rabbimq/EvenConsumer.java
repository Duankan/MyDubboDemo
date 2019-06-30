package com.github.Duankan.rabbimq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @author duankang
 * @date 2019-06-30
 * @class EvenConsumer 消费者具体类(专门处理偶数信息)
 * @desc 每天学习一点（明年月薪12k）
 */
public class EvenConsumer extends MessageConsumer {

    public EvenConsumer(ConnectionUtil connectionUtil) {
        super(connectionUtil);
    }

    @Override
    public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
        if (bytes == null)
            return;
        Map<String, Object> map = (Map<String, Object>) SerializationUtils.deserialize(bytes);
        int tagId = (Integer) map.get("tagId");
        if (tagId % 2 == 0) {
            //处理消息
            System.out.println("EvenConsumer接收并处理消息："+tagId);
            //通知服务器此消息已经被处理了
            connectionUtil.channel.basicAck(envelope.getDeliveryTag(), false);
        }else{
            //通知服务器消息处理失败，重新放回队列。false表示处理失败消息不放会队列，直接删除
            connectionUtil.channel.basicReject(envelope.getDeliveryTag(), true);
        }
    }

    @Override
    public void handleConsumeOk(String consumerTag) {
        this.consumerTag = consumerTag;
        System.out.println("EvenConsumer消费者：" + consumerTag + ",注册成功！");
    }

//    @Override
//    public void handleDelivery(String arg0, Envelope envelope, BasicProperties arg2, byte[] body) throws IOException {
//        if (body == null)
//            return;
//        Map<String, Object> map = (Map<String, Object>) SerializationUtils.deserialize(body);
//        int tagId = (Integer) map.get("tagId");
//        if (tagId % 2 == 0) {
//            //处理消息
//            System.out.println("EvenConsumer接收并处理消息："+tagId);
//            //通知服务器此消息已经被处理了
//            connectionUtil.channel.basicAck(envelope.getDeliveryTag(), false);
//        }else{
//            //通知服务器消息处理失败，重新放回队列。false表示处理失败消息不放会队列，直接删除
//            connectionUtil.channel.basicReject(envelope.getDeliveryTag(), true);
//        }
//    }
}
