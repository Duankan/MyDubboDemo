package com.github.Duankan.train;

/**
 * 工厂模式
 * 基本概念：为创建对象提供过渡接口，以便将创建对象的具体过程屏蔽隔离起来，达到提高灵活性的目的
 * 分为三类：
 * 简单工厂模式Simple Factory(又名静态工厂方法模式)：不利于产生系列产品；
 * 工厂方法模式Factory Method：又称为多形性工厂；
 * 抽象工厂模式Abstract Factory：又称为工具箱，产生产品族，但不利于产生新的产品；
 */
public class Factory {
    /**
     * 简单工厂模式
     */
    public static Object Sample(String className) {
        try {
            Class clazz = Class.forName(className);
            Object o = clazz.newInstance();
            return o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
