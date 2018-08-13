package com.github.Duankan.test;

import com.github.Duankan.utils.RedisUtil;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RedisTest{
//   @Autowired
//    IRedisService redisService;
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


}
