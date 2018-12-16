package com.github.Duankan.train.factory.abstractFactory;

import com.github.Duankan.train.factory.po.Plane;
import com.github.Duankan.train.factory.po.Tank;

/**
 * 具体工厂
 */
public class DefaultFactory extends AbstractFactory {
    @Override
    public Plane createPlane() {
        return new Plane();
    }
    @Override
    public Tank createTank() {
        return new Tank();
    }
}
