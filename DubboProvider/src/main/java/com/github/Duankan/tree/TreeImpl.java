package com.github.Duankan.tree;

/**
 * @author duankang
 * @date 2019-05-25
 * @func Tree的操作类impl
 */
public class TreeImpl implements Tree {
    private static Node root;

    public static Node getRoot() {
        return root;
    }

    public static void setRoot(Node root) {
        TreeImpl.root = root;
    }

    /**
     * @param
     * @construct null
     * @func 空构造函数
     */
    public TreeImpl() {
    }

    /**
     * @param node 二叉树节点
     * @construt node
     * @func 初始化二叉树构造函数
     */
    public TreeImpl(Node node) {
        this.root = node;
    }

    /**
     * @param key 节点的值
     * @return true/false
     * @func 1.当前树没有节点，该节点成为根节点
     * 2.当前节点比根节点小
     * 3.当前节点比根节点大
     */
    @Override
    public boolean insert(Object key) {
        int key_int = (int) key;
        Node newNode = new Node(key);
        //节点为空
        if (null == root) {
            root = newNode;
        } else {
            Node current = root;
            Node parent = null;
            while (null != current) {
                parent = current;
                if ((int) current.data > key_int) {//当前值比要出入的值大,搜索左子节点,把左子节点符合给当前节点
                    current = current.leftChild;
                    //当前节点为空，即左子节点空，直接插入
                    if (null == current) {
                        parent.leftChild = newNode;
                        return true;
                    }
                } else {
                    current = current.rightChild;
                    if (null == current) {
                        parent.rightChild = newNode;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * @param key 节点的内容
     * @return
     * @func 根据节点内容删除节点
     * @// FIXME: 2019/5/25  删除比较复杂 得注意变量赋值与值得改变
     * 1.删除的节点不存在子节点  直接删，让父节点的 左/右子节点变为null
     * 2.删除的节点存在子节点
     * A.需要处理好父节点和子节点关系,找比该节点大的最小值得中继节点替代
     * B.删除的是根节点
     */
    @Override
    public boolean delete(Object key) {
        int key_int = (int) key;
        Node current = root;
        Node delNode = null;
        Node parent = current;
        while (null != current) {
            if ((int) current.data > key_int) {//寻找左子节点
                parent = current;//记录下父节点
                current = current.leftChild;
            } else if ((int) current.data < key_int) {
                parent = current;//记录下父节点
                current = current.rightChild;
            } else {
                delNode = current;
                //该节点没有子节点,判断该删除的节点是父节点的左节点还是右节点
                if (delNode.leftChild == null && delNode.rightChild == null) {
                    if (parent.leftChild.data == delNode.data) {
                        parent.leftChild = null;
                    } else {
                        parent.rightChild = null;
                    }
                }
                //该节点有两个子节点
                else if (delNode.leftChild != null && delNode.rightChild != null) {
                    Node tidaiParent = delNode;//中继节点的父节点
                    Node tidaiNode = delNode.rightChild;//中继节点
                    while (tidaiNode != null) {
                        if (tidaiNode.leftChild != null) {
                            tidaiParent = tidaiNode;
                            tidaiNode = tidaiNode.leftChild;
                        } else {
                            break;
                        }
                    }
                    //如果删除的是根节点
                    if (parent.data == root.data) {
                        tidaiParent.leftChild = null;
                        root.data = tidaiNode.data;
                        return true;
                    }
                    //删除的节点不是根节点，且节点在根节点左边
                    if (parent.leftChild.data == delNode.data) {
                        //如果删除的节点和替代的父节点是同一节点（删除的节点，能替代他的节点是他的右子节点）
                        if (delNode.data == tidaiParent.data) {
                            parent.rightChild = tidaiNode;
                            tidaiNode.leftChild = delNode.leftChild;
                        } else {
                            tidaiNode.leftChild = delNode.leftChild;
                            tidaiNode.rightChild = delNode.rightChild;
                            tidaiParent.leftChild = null;
                            parent.leftChild = tidaiNode;
                        }
                    }
                    //删除的节点不是根节点，且节点在根节点右侧
                    else {
                        //如果删除的节点和替代的父节点是同一节点（删除的节点，能替代他的节点是他的右子节点）
                        if (delNode.data == tidaiParent.data) {
                            parent.rightChild = tidaiNode;
                            tidaiNode.leftChild = delNode.leftChild;
                        } else {
                            tidaiNode.leftChild = delNode.leftChild;
                            tidaiNode.rightChild = delNode.rightChild;
                            tidaiParent.leftChild = null;
                            parent.rightChild = tidaiNode;
                        }
                    }
                }
                //该节点有一个节点：左子节点
                else if (delNode.leftChild != null && delNode.rightChild == null) {
                    if (parent.leftChild.data == delNode.data) {
                        parent.leftChild = delNode.leftChild;
                    } else {
                        parent.rightChild = delNode.leftChild;
                    }
                }
                //该节点有一个节点:右节点
                else {
                    if (parent.leftChild.data == delNode.data) {
                        parent.leftChild = delNode.rightChild;
                    } else {
                        parent.rightChild = delNode.rightChild;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * @return
     */
    public boolean deleteRootNode() {
        return true;
    }

    /**
     * @param key 节点内容
     * @return Node
     * @func 查找节点
     */
    @Override
    public Node find(Object key) {
        int key_int = (int) key;
        Node curret = root;
        while (null != curret) {
            if ((int) curret.data > key_int) {//当前值比查找的值大
                curret = curret.leftChild;
            } else if ((int) curret.data < key_int) {
                curret = curret.rightChild;
            } else {
                return curret;
            }
        }
        return null;//遍历完没有找到该节点
    }

    /**
     * @param
     * @return void
     * @func 中序遍历 左>根>右  如:10 8 7 9 13 11 12
     * @// FIXME: 2019/5/25 执行代码解析：
     * 1.根节点10，走到0处->1处递归 10在1处停留等待
     * 2.子节点8走到0处->1处再递归  8在1处等待
     * 3.字节点7走到0处->1处再递归  7在1处等待
     * 4.null不走If代码块，此处走完了，7在1处等待结束，往下走!
     * 5.依次类推
     */
    @Override
    public void inMiddleOrder(Node current) {
        if (null != current) {//代码0处
            inMiddleOrder(current.leftChild);//代码1处
            System.out.print(current.data + " "); //代码2处
            inMiddleOrder(current.rightChild);//代码3处
        }
    }

    /**
     * @param
     * @return void
     * @func 前序遍历  根>左>右
     */
    @Override
    public void preOrder(Node current) {
        if (null != current) {
            System.out.print(current.data + " ");
            preOrder(current.leftChild);
            preOrder(current.rightChild);
        }
    }

    /**
     * @param
     * @return void
     * @func 后序遍历 左>右>根
     */
    @Override
    public void lastOrder(Node current) {
        if (current != null) {
            lastOrder(current.leftChild);
            lastOrder(current.rightChild);
            System.out.print(current.data + " ");
        }
    }

    /**
     * @return Node
     * @func 寻找二叉树的最大值
     * @// FIXME: 2019/5/25 找根的右子节点，一直到没有右子节点
     */
    @Override
    public Node findMax() {
        Node current = root;
        Node maxNode = current;
        while (null != current) {
            maxNode = current;
            current = current.rightChild;
        }
        return maxNode;
    }

    /**
     * @return Node
     * @func 寻找二叉树的最小值
     * @// FIXME: 2019/5/25 找根节点的最左子节点，一直到没有左子节点
     */
    @Override
    public Node findMin() {
        Node current = root;
        Node minNode = current;
        while (null != current) {
            minNode = current;
            current = current.leftChild;
        }
        return minNode;
    }
}
