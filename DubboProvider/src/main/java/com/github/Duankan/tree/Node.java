package com.github.Duankan.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author duankang
 * @class 二叉树
 * @desc 深探二叉树
 */
public class Node {
    private Logger logger = LoggerFactory.getLogger(Node.class);
    public Object data;//节点数据
    public Node leftChild;//左节点
    public Node rightChild;//右节点

    public void display() {
        logger.info(data.toString());
    }

    public Node() {
    }

    public Node(Object data) {
        this.data = data;
    }
}
