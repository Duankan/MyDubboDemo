package com.github.Duankan.train.factory.po;

import com.github.Duankan.train.factory.factoryMethod.Moveable;
public class Plane implements Moveable{
    @Override
    public void run() {
        System.out.println("造飞机");
    }
    @Override
    public void price() {
        System.out.println("飞机售价1000万");
    }
}
