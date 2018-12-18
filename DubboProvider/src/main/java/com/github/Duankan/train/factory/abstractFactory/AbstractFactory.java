package com.github.Duankan.train.factory.abstractFactory;
import com.github.Duankan.train.factory.po.Plane;
import com.github.Duankan.train.factory.po.Tank;

/**
 * 抽象类工厂(把所有的抽象的方法全部放进一个抽象类中)
 */
public abstract class AbstractFactory {
    public abstract Plane createPlane();
    public abstract Tank createTank();}
