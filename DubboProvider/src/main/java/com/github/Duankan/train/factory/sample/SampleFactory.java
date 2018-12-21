package com.github.Duankan.train.factory.sample;

/**
 * 简单工厂（）
 */
public class SampleFactory {
    public static Object getIntance(String className){
        try {
            Class clazz=Class.forName(className);
            Object o=clazz.newInstance();
            return o;
        }
        catch (Exception e){
        }
        return null;
    }
}
