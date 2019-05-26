package com.github.Duankan.tree;

/**
 * @author duankang
 * @class 二叉树的具体方法
 * @func 封装操作二叉树的具体方法
 */
public interface Tree {
    //增
    public boolean insert(Object key);

    //删
    public boolean delete(Object key);

    //查
    public Node find(Object key);

    //中序遍历
    public void inMiddleOrder(Node current);

    //前序遍历
    public void preOrder(Node current);

    //后序遍历
    public void lastOrder(Node current);

    //寻找最大值
    public Node findMax();

    //寻找最小值
    public Node findMin();
}
