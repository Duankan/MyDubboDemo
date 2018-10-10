package com.github.Duan.Consumer.proxy;

/**
 * 动态接口实现类
 */
public class ProxyImpl implements ProxyDemoInterface {
    @Override
    public void dosth() {
        System.out.println("do something!");
    }
}
