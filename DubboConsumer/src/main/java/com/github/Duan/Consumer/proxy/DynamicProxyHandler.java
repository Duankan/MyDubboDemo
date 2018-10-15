package com.github.Duan.Consumer.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * #-----------------------------------###
 * ###-----代理类工厂--（jdk代理）-----###
 * #-----------------------------------###
 */
public class DynamicProxyHandler {
    private Object target;

    public DynamicProxyHandler(Object proxyed) {
        this.target = proxyed;
    }
    public Object getProxyInstance(){
        //被代理的类加载器
        ClassLoader loader = target.getClass().getClassLoader();
        //被代理类的接口
        Class<?>[] interfaces=target.getClass().getInterfaces();
        //代理类执行
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("代理工作了！");
                return method.invoke(target,args);
            }
        };
        //返回代理类
        Object proxy = Proxy.newProxyInstance(loader, interfaces, invocationHandler);
        return proxy;
    }
}
