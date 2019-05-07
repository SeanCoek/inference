package com.tree;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import javax.swing.tree.TreeNode;

import org.w3c.dom.css.ElementCSSInlineStyle;

import com.tree.TrieTree.Node;

public class MainAlgorithm {

	public void mainAlgorithm(TrieTree T){
		int i=0;
		int count=0;
		ArrayList<Node> redList=new ArrayList<>();
		ArrayList<Node> blueList=new ArrayList<>();
		ArrayList<Node> greenList=new ArrayList<>();
		ArrayList<Node> mergeSet=new ArrayList<>();
		Map<Node,Node> mergeMap=new HashMap<Node,Node>(); 
		boolean merged = false;
		//初始化
		redList.add(T.root);
		Collections.addAll(blueList, T.root.child);
		while(blueList.size()!=0){
			i=0;
			merged=false;
			count=0;
			Node stdNode=redList.get(count);
			//如果结点为空，去除
			while(i<blueList.size()){
				Node eNode=blueList.get(i);
				if(eNode==null){
					blueList.remove(eNode);
				}else{
					i++;
				}
			}
			Node tempNode=blueList.get(0);
			while(count<redList.size()){
				//比较状态，返回一个值
				stdNode=redList.get(count);
				int result=compare(stdNode,tempNode);
				if(result<=0){
					mergeSet.add(tempNode);
					mergeMap.put(tempNode,stdNode);
					greenList.add(tempNode);
					blueList.remove(tempNode);
					merged=true;
					break;
				}else if(result>0){
					redList.remove(stdNode);
					greenList.add(stdNode);
					mergeSet.add(stdNode);
					mergeMap.put(stdNode, tempNode);
					//修改映射
//					mergeMap.get(stdNode);
//					。。。
				}
				count++;
			}
			if(!merged){
				redList.add(tempNode);
				Collections.addAll(blueList, tempNode.child);
				blueList.remove(tempNode);
			}
		}
	}
	
	public int compare(Node redNode,Node blueNode){
		int i=0,j=0;
		Queue<Node> q= new LinkedList<>();
		Queue<Node> p= new LinkedList<>();
		while(redNode!=null||blueNode!=null){
			if(redNode.access!=blueNode.access){
				return 1;
			}else{
				Collections.addAll(q, redNode.child);
				Collections.addAll(p, blueNode.child);
				i=0;j=0;
				for(Node node: q){
					i++;
					node=q.poll();
					if(node!=null){
						redNode=node;
						break;
					}
				}
				for(Node node: p){
					j++;
					node=p.poll();
					if(node!=null){
						blueNode=node;
						break;
					}
				}
				if(i!=j){
					return 1;
				}else{
					compare(redNode, blueNode);
				}
			}
		}
		
		
		return 0;
	}
	
	public void GenerateDFA(ArrayList<Node> redList,Map<Node,Node> mergeMap){
		int index=0;
		Node firstNode=redList.get(index);
		
	}
}
