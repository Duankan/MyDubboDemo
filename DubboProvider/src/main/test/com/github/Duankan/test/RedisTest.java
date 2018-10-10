package com.github.Duankan.test;

import com.github.Duankan.po.GitPo;
import com.github.Duankan.utils.RedisUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class RedisTest extends BaseTest {
    //   @Autowired
//    IRedisService redisService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    RedisTemplate clusterRedisTemplate;
    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-redis.xml");
        RedisUtil redisUtil = (RedisUtil) context.getBean("redisUtil");
        boolean flag = redisUtil.exists("age");
        System.out.println(flag);
        Object value = redisUtil.get("dankin");
//        redisUtil.set("dankin","12345");
        System.out.println(value);
//        redisUtil.get("com.zkh.service.impl.MovieServiceImpl_selectPage_1_10_6");
//        System.out.println(redisUtil.get("com.zkh.service.impl.MovieServiceImpl_selectPage_1_10_6"));
//        redisUtil.remove("(needed");
    }

    @Test
    public void test2() {
        System.out.println(redisUtil);
        System.out.println(redisUtil.get("liqingqing"));
        System.out.println(redisUtil.get("com.github.Duankan.service.impl.DubboServiceImpl_queryById_9"));
    }

    /**
     * 测试没有使用ssm集成，没有序列化与反序列化
     */
    @Test
    public void test3() {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //设置 redis 字符串数据
//        jedis.set("runoobkey", "学习redis");
//        System.out.println(jedis.get("dankin"));
        GitPo gitPo = new GitPo();
        gitPo.setName("李青青");
        gitPo.setAddr("湖北襄阳");//set支持string和byte
//        jedis.set("gitpo".getBytes(), serialize(gitPo));
    }

    /**
     * 测试单机版和集群版
     */
    @Test
    public void test4() {
        clusterRedisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection)
                    throws DataAccessException {
                byte[] keyb = "qq".getBytes();
                byte[] valueb = "cluster".getBytes();
                // 判断当前值是否已经存在
                if (connection.exists(keyb)) {
                    // 删除原数据
                    connection.del(keyb);
                }
//                connection.set(keyb, valueb);
                return 1L;
            }
        });


    }


}
