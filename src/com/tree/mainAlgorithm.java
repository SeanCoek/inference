//package com.tree;
//
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//import com.tree.TrieTree.Node;
//
//public class mainAlgorithm {
//
//	public void mainAlgorithm(TrieTree T){
//		int index=0;
//		int count=0;
//		ArrayList<Node> redList=new ArrayList<>();
//		ArrayList<Node> blueList=new ArrayList<>();
//		ArrayList<Node> greenList=new ArrayList<>();
//		ArrayList<Node> mergeSet=new ArrayList<>();
//		Map<Node,Node> mergeMap=new HashMap<Node,Node>();
//		boolean merged = false;
//		//初始化
//		redList.add(T.root);
//		blueList.add(T.root.next);
//		while(blueList.size()!=0){
//			Node stdNode=redList.get(count);
//			Node tempNode=blueList.get(index);
//			while(count<redList.size()){
//				//比较状态，返回一个值
//				int result=compare(stdNode,tempNode);
//				if(result<=0){
//					mergeSet.add(tempNode);
//					mergeMap.put(tempNode,stdNode);
//					greenList.add(tempNode);
//					blueList.remove(tempNode);
//					merged=true;
//					break;
//				}else if(result>0){
//					redList.remove(stdNode);
//					greenList.add(stdNode);
//					mergeSet.add(stdNode);
//					mergeMap.put(stdNode, tempNode);
//					//修改映射
//					mergeMap.get(stdNode);
//					。。。
//				}
//				count++;
//			}
//			if(!merged){
//				redList.add(tempNode);
//				blueList.add(tempNode.next);
//				blueList.remove(tempNode);
//			}
//			index++;
//		}
//	}
//
//
//}
