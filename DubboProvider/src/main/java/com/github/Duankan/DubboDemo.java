package com.github.Duankan;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class DubboDemo {
    private static final Log log = LogFactory.getLog(DubboDemo.class);
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:provider.xml");
        context.start();
        System.in.read();
//        try {
//            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:provider.xml");
//            context.start();
//        } catch (Exception e) {
//            log.error("== DubboProvider context start error:", e);
//        }
//        synchronized (DubboDemo.class) {
//            while (true) {
//                try {
//                    DubboDemo.class.wait();
//                } catch (InterruptedException e) {
//                    log.error("== synchronized error:", e);
//                }
//            }
//        }
    }
}
