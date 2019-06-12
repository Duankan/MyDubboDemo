package com.github.Duankan.redis.queue;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

/**
 * @author duankang
 * @date 2019-06-08
 * @class 探究redis做消息队列>>>生产者
 * @// FIXME: 2019/6/8  每天学习一点
 */
public class Producer extends Thread {
    private static Logger logger = Logger.getLogger(Producer.class);
    public static final String message_key = "msg_queue";//队列名
    private Jedis jedis;//操作Jedis实例对象
    private String producerName;//生产者名
    private volatile int count;//队列中待处理的数量

    /**
     * @param name 生产者名
     * @method 注入名字的构造函数
     * @desc
     */
    public Producer(String name) {
        this.producerName = name;
        this.init();
    }

    /**
     * @param
     * @method 初始化jedis实例对象
     * @desc
     */
    public void init() {
        this.jedis = new Jedis("localhost");
    }

    /**
     * @param message 入列信息
     * @method 向队列中存放信息
     * @desc
     */
    public void putMessage(String message) {
        long size = jedis.lpush(message_key, message);
        logger.info(producerName + "当前未被处理的消息条数:" + size);
        count++;
    }

    /**
     * @return count藏宝阁度
     * @desc
     * @method 获得队列目前长度
     */
    public long getCount() {
        return count;
    }

    @Override
    public void run() {
        try {
            while (true) {
                long current = System.currentTimeMillis();
                putMessage(String.valueOf(current));
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        Producer producer = new Producer("myproducer");
        producer.start();
        for (; ; ) {
            logger.info("main : 已存储消息条数:" + producer.getCount());
            TimeUnit.SECONDS.sleep(10);
        }
    }
}
