package com.github.Duankan.redis.pubandsub;

import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

/**
 * @author duankang
 * @date 2019-06-08
 * @class redis消息发布
 * @// FIXME: 2019/6/8  每天学习一点
 */
public class Publisher {

    public static final String CHANNEL_KEY = "channel:message";
    private Jedis jedis;

    public Publisher() {
        jedis = new Jedis("localhost");
    }

    public void publishMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return;
        }
        jedis.publish(CHANNEL_KEY, message);
    }

    public static void main(String[] args) {
        Publisher publisher = new Publisher();
        publisher.publishMessage("exi");
    }
}

