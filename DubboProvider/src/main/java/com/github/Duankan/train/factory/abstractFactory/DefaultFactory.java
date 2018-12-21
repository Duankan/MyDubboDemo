package com.github.Duankan.train.factory.abstractFactory;

import com.github.Duankan.train.factory.po.Plane;
import com.github.Duankan.train.factory.po.Tank;

/**
 * 具体工厂  抽象工厂模式Abstract Factory：又称为工具箱，产生产品族，但不利于产生新的产品
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
