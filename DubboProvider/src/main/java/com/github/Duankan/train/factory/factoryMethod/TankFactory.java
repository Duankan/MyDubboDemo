package com.github.Duankan.train.factory.factoryMethod;

import com.github.Duankan.train.factory.po.Tank;

/**
 * 具体工厂
 */
public class TankFactory extends VeicleFactory{
    @Override
    public Moveable create() {
        return new Tank();
    }
}
