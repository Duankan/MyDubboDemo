package com.github.Duankan.test;

import com.github.Duankan.utils.RedisUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RedisTest extends BaseTest{
//   @Autowired
//    IRedisService redisService;
    @Autowired
    RedisUtil redisUtil;
    @Test
    public void test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-redis.xml");
        RedisUtil redisUtil= (RedisUtil) context.getBean("redisUtil");
        boolean flag=redisUtil.exists("age");
        System.out.println(flag);
        Object value = redisUtil.get("tanmingmin");
        System.out.println(value);
//        redisUtil.get("com.zkh.service.impl.MovieServiceImpl_selectPage_1_10_6");
//        System.out.println(redisUtil.get("com.zkh.service.impl.MovieServiceImpl_selectPage_1_10_6"));
//        redisUtil.remove("(needed");
    }
    @Test
    public void test2(){
        System.out.println(redisUtil);
        System.out.println(redisUtil.get("com.github.Duankan.service.impl.DubboServiceImpl_queryById_9"));
    }


}
