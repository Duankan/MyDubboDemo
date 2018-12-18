package com.github.Duankan.train.factory.factoryMethod;

import com.github.Duankan.train.factory.po.Plane;

/**
 * 具体工厂
 */
public class PlaneFactory extends VeicleFactory{
    @Override
    public Moveable create() {
        return new Plane();
    }
}
