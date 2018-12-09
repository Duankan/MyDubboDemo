package com.github.Duankan.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.Duankan.utils.RedisUtil;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.io.Serializable;
import java.util.*;

public class RedisTest extends BaseTest {
    //   @Autowired
//    IRedisService redisService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    RedisTemplate clusterRedisTemplate;
    @Autowired
    JedisConnectionFactory connectionFactory;
    public RedisTemplate<String,Object> redisTemplate=null;
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
        jedis.set("runoobkey", "学习redis");
        System.out.println(jedis.get("runoobkey"));
//        GitPo gitPo = new GitPo();
//        gitPo.setName("李青青");
//        gitPo.setAddr("湖北襄阳");//set支持string和byte
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
    @Test
    public void test5(){
        //一次set多个key与一次获取多个key的String结构到缓存
//        Map<String,String> maps = new HashMap<String, String>();
//        maps.put("multi1","multi1");
//        maps.put("multi2","multi2");
//        maps.put("multi3","multi3");
//        redisTemplate.opsForValue().multiSet(maps);
//        List<String> keys = new ArrayList<String>();
//        keys.add("multi1");
//        keys.add("multi2");
//        keys.add("multi3");
//        System.out.println(redisTemplate.opsForValue().multiGet(keys));
        //存放list结构数据
//        List list=new ArrayList();
//        list.add(1);
//        list.add("liqingqing");
//        redisTemplate.opsForList().leftPushAll("list",list);
//        System.out.println(redisTemplate.opsForList().range("list",0,-1));
//        redisTemplate.delete("list");
        //存放hah结构数据
//        Map hp=new HashMap();
//        hp.put("name","dankin");
//        hp.put("age",26);
//        hp.put("school","wtu");
//        redisTemplate.opsForHash().putAll("peo",hp);
//        System.out.println(redisTemplate.opsForHash().entries("peo"));
        //存放set结构数据
        String[] strings=new String[]{"aaa","bbb","aaa"};
        redisTemplate.opsForSet().add("set",strings);
        System.out.println(redisTemplate.opsForSet().members("set"));
    }

    /**
     * 初始化redisTemplate并赋予不同的数据结构不同的序列化方式
     * 1.String结构：string序列化
     * 2.Hash结构：jdk序列化
     * 3.list结构：jdk序列化
     * 4.zset结构：
     * 5.set结构：
     */
    @Before
    public void initRedisTemplate(){
        //jdk序列化？
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        //sting序列化
        StringRedisSerializer stringRedisSerializer=new StringRedisSerializer();
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        redisTemplate=new RedisTemplate();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
    }


}
