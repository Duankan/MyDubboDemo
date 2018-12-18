package com.github.Duankan.train.factory.po;

import com.github.Duankan.train.factory.factoryMethod.Moveable;

public class Tank implements Moveable{
    @Override
    public void run() {
        System.out.println("造坦克");
    }

    @Override
    public void price() {
        System.out.println("坦克售价3000万");
    }
}
