package com.github.Duankan.rabbimq;

import java.io.IOException;
import java.util.HashMap;

public class Client {

    public static void main(String[] args) {
        new Client();
    }

    public Client() {
        try {
            //发消息
            publishMessage();
            //注册消费者
            addConsumer();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void publishMessage() throws IOException, InterruptedException {
        //建立连接，创建通道，声明队列
        ConnectionUtil connectionUtil = new ConnectionUtil("李青青");
        //创建生产者
        MessageProducer producer = new MessageProducer(connectionUtil);
        //生产者消息确认机制（即ack）
        connectionUtil.channel.confirmSelect();
        //注册消息确认监听器。注意：返回的时候Return在前，Confirm在后
        connectionUtil.channel.addConfirmListener(new MyConfirmListener());
        connectionUtil.channel.addReturnListener(new MyReturnListener());

        int i = 1;
        while (i <= 10) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("tagId", i);
            map.put("content", "努力地挣钱");
            producer.sendMessage(map);
            i++;
        }
    }

    public void addConsumer() throws IOException {
        //这不是重复建立连接？
        ConnectionUtil connectionUtil = new ConnectionUtil("李青青");
        OddConsumer odd = new OddConsumer(connectionUtil);
        odd.basicConsume();
        EvenConsumer even = new EvenConsumer(connectionUtil);
        even.basicConsume();
    }

}