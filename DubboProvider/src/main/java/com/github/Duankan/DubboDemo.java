package com.github.Duankan;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class DubboDemo {
//    private static final Log log = LogFactory.getLog(DubboDemo.class);
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:provider.xml");
        context.start();
        System.in.read();
    }
}
