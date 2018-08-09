package com.github.Duan.Consumer;

import com.github.Duankan.service.IDubboService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Demo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:dubbo-demo-consumer.xml");
        context.start();
        IDubboService demoProviderService=(IDubboService) context.getBean("Mydemo");
        String result=demoProviderService.say("nima");
        System.out.println("消费者："+result);
        try {
            System.in.read();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        context.close();
    }
}
