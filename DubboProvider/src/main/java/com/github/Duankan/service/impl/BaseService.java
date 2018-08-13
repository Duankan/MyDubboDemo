package com.github.Duankan.service.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

//用来加载redis
public class BaseService {
    public static ClassPathXmlApplicationContext context;
    static {
         context = new ClassPathXmlApplicationContext("classpath:spring-redis.xml");
    }
    public static ClassPathXmlApplicationContext getContext(){
        return context;
    }
}
