package com.github.Duankan.train;

/**
 * 单列模式(巩固知识点)
 * 基本概念：保证一个类仅有一个实例，并提供一个访问它的全局访问点。
 */
public class Single {
    private static Single instance;//赖汉模式
    //私有化，不让外部类直接New
    private Single(){}
    public static Single getInstance(){
        if(instance==null){
            instance=new Single();
        }
        return instance;
    }
    public void sayHello(){
        System.out.println("hello!");
    }
}
