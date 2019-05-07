/**
 * 
 * 提供hasStr、insert、countPrefix、preWalk、getRoot接口
 * @author 
 */
package com.tree;

public class TrieTree {

    public final int SIZE = 100;  //每个节点能包含的子节点数，即需要SIZE个指针来指向其孩子
    public Node root;   //字典树的根节点
	//public int access;

    /**
     * 字典树节点类
     * @author
     */
    public class Node {
        public int access;      //标识该节点是否为可接受状态
        public int num;         //标识经过该节点的字符串数。在计算前缀包含的时候会用到
        public Node[] child;    //该节点的子节点

        public Node() {
            child = new Node[SIZE];
            //setAccess(0);
            //num = 1;
        }

		
    }

	
    public TrieTree() {
        root = new Node();
    }
    
    /**
     * 在字典树中插入一个单词
     * @param word,assess
     */
    public void insert(String word,int access) {
        if (word == null || word.isEmpty()) {
            return;
        }
        Node pNode = this.root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - '!';
            if (pNode.child[index] == null) {   //如果不存在节点，则new一个节点插入字典树
                Node tmpNode = new Node();
                pNode.child[index] = tmpNode;
            } else {
                pNode.child[index].num++;       //如果字典树中改路径上存在节点，则num加1，表示在该节点上有一个新的单词经过
            }
            pNode = pNode.child[index];
        }
        pNode.access = access;
    }
    
    /**
     * 比较两个结点
     */
    public void compare(){

    }


    
}