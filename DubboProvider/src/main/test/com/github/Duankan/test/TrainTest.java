package com.github.Duankan.test;

import com.github.Duankan.train.Single;
import com.github.Duankan.train.factory.abstractFactory.AbstractFactory;
import com.github.Duankan.train.factory.abstractFactory.DefaultFactory;
import com.github.Duankan.train.factory.po.Plane;
import com.github.Duankan.train.factory.po.Tank;
import com.github.Duankan.tree.Node;
import com.github.Duankan.tree.Tree;
import com.github.Duankan.tree.TreeImpl;
import org.junit.Test;

import javax.sound.midi.Soundbank;

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
        //测试简单工厂:扩展性差，还得修改工厂类和增加产品类
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
        AbstractFactory factory = new DefaultFactory();
        Plane plane = factory.createPlane();
        plane.run();
        plane.price();
        Tank tank = factory.createTank();
        tank.run();
        tank.price();
    }

    @Test
    public void RefAndValueTransforn() {
        Node node = new Node(1);
        Node node1 = node;
        node1.data = 2;
        System.out.println(node.data);
        System.out.println(node1.data);
    }

    @Test
    public void initTree() {
        Node node = new Node(10);
        Tree tree = new TreeImpl(node);
        System.out.println("根内容：" + TreeImpl.getRoot().data);
        System.out.println("根左子节点：" + TreeImpl.getRoot().leftChild);
        System.out.println("根右子节点：" + TreeImpl.getRoot().rightChild);


    }

    @Test
    public void insertTreeTest() {
        Node node = new Node(11);
        Tree tree = new TreeImpl(node);
        tree.insert(7);
        tree.insert(5);
        tree.insert(9);
        tree.insert(8);
        tree.insert(10);
        tree.insert(14);
        tree.insert(12);
        tree.insert(16);
        tree.insert(15);
        tree.insert(17);
        System.out.println("中序遍历：");
        tree.inMiddleOrder(TreeImpl.getRoot());
        System.out.println("");
        System.out.println("前序遍历:");
        tree.preOrder(TreeImpl.getRoot());
        System.out.println("");
//        System.out.println("根内容：" + TreeImpl.getRoot().data);
//        System.out.println("根左子节点：" + TreeImpl.getRoot().leftChild.data);
//        System.out.println("根右子节点：" + TreeImpl.getRoot().rightChild.data);
//        System.out.println("最大节点：" + tree.findMax().data);
//        System.out.println("最小节点：" + tree.findMin().data);

        System.out.println("开始测试删除节点14:");
        tree.delete(9);
        System.out.println("中序遍历：");
        tree.inMiddleOrder(TreeImpl.getRoot());
        System.out.println("");
        System.out.println("前序遍历：");
        tree.preOrder(TreeImpl.getRoot());
        System.out.println("");
        System.out.println("后序遍历：");
        tree.lastOrder(TreeImpl.getRoot());
    }
}
