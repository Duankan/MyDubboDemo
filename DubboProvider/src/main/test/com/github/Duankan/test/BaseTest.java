package com.github.Duankan.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring-redis.xml"})
//@ContextConfiguration(locations={"classpath*:spring-mybatis.xml","classpath*:spring-redis.xml"})
//@ContextConfiguration(locations={"classpath*:spring-redisCluster.xml","classpath*:spring-redis.xml"})
public class BaseTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testApplicationContext(){
        assertNotNull(applicationContext);
//        System.out.println(applicationContext);
    }
}
