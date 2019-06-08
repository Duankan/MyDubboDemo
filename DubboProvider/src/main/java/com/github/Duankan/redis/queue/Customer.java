package com.github.Duankan.redis.queue;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author duankang
 * @date 2019-06-08
 * @class redis做消息队列>>>消费者
 * @// FIXME: 2019/6/8  每条学习一点
 */
public class Customer extends Thread {
    private static Logger logger = Logger.getLogger(Producer.class);
    private String customerName;
    private volatile int count;
    private Jedis jedis;

    public Customer(String name) {
        this.customerName = name;
        init();
    }

    private void init() {
        jedis = new Jedis("localhost");
    }

    /**
     * @method 消息出列
     * @warn 1.不停的rpop会不断地建立连接，浪费资源
     * 2.使用brpop指令，这个指令只有在有元素时才返回，没有则会阻塞直到超时返回null
     */
    public void processMessage() {
        //1.lpop代码片段
//        String message = jedis.rpop(Producer.message_key);
//        if (message != null) {
//            count++;
//            handle(message);
//        }

        //2.brpop代码片段
        List<String> messages = jedis.brpop(0, Producer.message_key, "testKey");
        if (messages.size() != 0) {
            //由于该指令可以监听多个Key,所以返回的是一个列表 列表由2项组成，1> 列表名，2>数据
            String keyName = messages.get(0);
            //如果返回的是MESSAGE_KEY的消息
            if (Producer.message_key.equals(keyName)) {
                String msg = messages.get(1);
                count++;
                handle(msg);
            }

        }
    }

    public void handle(String message) {
        logger.info(customerName + " 正在处理消息,消息内容是: " + message + " 这是第" + count + "条");
    }

    @Override
    public void run() {
        while (true) {
            processMessage();
        }
    }

    public static void main(String[] args) {
        Customer customer = new Customer("yamikaze");
        customer.start();
    }
}
