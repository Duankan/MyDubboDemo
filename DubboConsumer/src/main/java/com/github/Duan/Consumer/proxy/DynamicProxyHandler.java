package com.github.Duan.Consumer.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理类
 */
public class DynamicProxyHandler implements InvocationHandler{
    protected Object proxy;

    public DynamicProxyHandler(Object proxy) {
        this.proxy = proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理工作了！");
        return method.invoke(proxy,args);
    }
}
