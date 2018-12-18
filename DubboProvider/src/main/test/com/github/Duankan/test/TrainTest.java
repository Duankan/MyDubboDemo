package com.github.Duankan.test;

import com.github.Duankan.train.Factory;
import com.github.Duankan.train.Single;
import com.github.Duankan.train.factory.abstractFactory.AbstractFactory;
import com.github.Duankan.train.factory.abstractFactory.DefaultFactory;
import com.github.Duankan.train.factory.factoryMethod.Moveable;
import com.github.Duankan.train.factory.factoryMethod.PlaneFactory;
import com.github.Duankan.train.factory.factoryMethod.TankFactory;
import com.github.Duankan.train.factory.factoryMethod.VeicleFactory;
import com.github.Duankan.train.factory.po.Plane;
import com.github.Duankan.train.factory.po.Tank;
import com.github.Duankan.train.factory.sample.SampleFactory;
import org.junit.Test;

/**
 * 设计模式的测试类
 */
public class TrainTest {
    //1.测试单例模式
    @Test
    public void singleTest() {
        Single single = Single.getInstance();
        single.sayHello();
    }
    //2.测试工厂模式
    @Test
    public void factoryTest() {
        //测试简单工厂
//        Plane plane= (Plane) SampleFactory.getIntance("com.github.Duankan.train.factory.po.Plane");
//        plane.run();
//        Tank tank= (Tank) SampleFactory.getIntance("com.github.Duankan.train.factory.po.Tank");
//        tank.run();
        //测试工厂方法模式(缺点是：当产品类增多，产品类工厂（PlaneFactory）也增多，不好！！)
//        VeicleFactory factory = new PlaneFactory();
//        Moveable m = factory.create();
//        m.run();
//        factory = new TankFactory();
//        m = factory.create();
//        m.run();
        //测试抽象工厂方法模式
        AbstractFactory factory=new DefaultFactory();
        Plane plane=factory.createPlane();
        plane.run();
        plane.price();
        Tank tank=factory.createTank();
        tank.run();
        tank.price();
    }
}
