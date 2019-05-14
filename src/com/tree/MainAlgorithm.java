package com.tree;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;

import com.tree.Node;

public class MainAlgorithm {

	public void mainAlgorithm(Node root){
		ArrayList<Node> redList=new ArrayList<>();
		ArrayList<Node> blueList=new ArrayList<>();
		ArrayList<Node> greenList=new ArrayList<>();
		ArrayList<Node> mergeSet=new ArrayList<>();
		Map<Node,Node> mergeMap=new HashMap<Node,Node>(); 

		//初始化
		redList.add(root);
		blueList.addAll(root.child);
		while(blueList.size()!=0){
			boolean merged = false;
			int idx=0;
			Node blueNode=blueList.get(0);
			while(idx < redList.size()){
				Node redNode=redList.get(idx);
//				int result=compare(redNode,blueNode);
				if(compare(blueNode, redNode)){
					Node.equalsType = 1;
					mergeSet.add(blueNode);
					mergeMap.put(blueNode,redNode);
					Node.equalsType = 0;
					greenList.add(blueNode);
					blueList.remove(blueNode);
					merged=true;
					break;
				}else if(compare(redNode, blueNode)){
					redList.remove(idx);
					greenList.add(redNode);
					Node.equalsType = 1;
					mergeSet.add(redNode);
					mergeMap.put(redNode, blueNode);
					Iterator<Map.Entry<Node, Node>> entries = mergeMap.entrySet().iterator();
					while (entries.hasNext()) {
						Map.Entry<Node, Node> entry = entries.next();
						if(entry.getValue().equals(redNode)){
							mergeMap.put(entry.getKey(),blueNode);
						}
					}
					Node.equalsType = 0;
				} else {
					idx++;
				}
			}
			if(!merged){
				redList.add(blueNode);
				blueList.remove(0);
				blueList.addAll(blueNode.child);
			}
		}

		generateDFA(redList, mergeMap);
	}

	/***
	 *
	 * @param node1
	 * @param node2
	 * @return 1 for w <= w', -1 for w' <= w, 0 for else
	 */
	public boolean compare(Node node1,Node node2){

		if(node1.type != node2.type) {
			return false;
		}
		List<Node> node1Child = new ArrayList<>();
		List<Node> node2Child = new ArrayList<>();
		node1Child.addAll(node1.child);
		node2Child.addAll(node2.child);

		if(node1Child.size() == 0) {
			return true;
		}

		Node.equalsType = Node.EQUALS_VALUE_TYPE;
		for(Node childOfNode1: node1Child) {
			if(node2Child.indexOf(childOfNode1) == -1) {
				return false;
			}
		}
		for(Node childOfNode1: node1Child) {
			Node childOfNode2 = node2Child.get(node2Child.indexOf(childOfNode1));
			if(!compare(childOfNode1, childOfNode2)) {
				return false;
			}
		}
		Node.equalsType = Node.EQUALS_VALUE;

		return true;

	}

	
	public void generateDFA(ArrayList<Node> redList,Map<Node,Node> mergeMap){
		Map<String, Integer> nodeMap = new HashMap<>();
		Map<String, Integer> transition = new HashMap<>();
		Set<String> finalStates = new HashSet<>();
		Set<String> states = new HashSet<>();
		for(int i=0; i<redList.size(); i++) {
			Node redNode = redList.get(i);
			states.add(redNode.word);
			if(redNode.type == 1) {
				finalStates.add(redNode.word);
			}
			nodeMap.put(redNode.word, i);
		}
		for(int i=0; i<redList.size(); i++) {
			Node redNode = redList.get(i);
			List<Node> child = redNode.child;
			for(int j=0; j<child.size(); j++) {
				String childWord = child.get(j).word;
				if(nodeMap.containsKey(childWord)) {
					transition.put(redNode.word + ":" + childWord.substring(childWord.length()-1), nodeMap.get(childWord));
				}
			}
		}

		Iterator<Map.Entry<Node, Node>> iterator = mergeMap.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry<Node, Node> entry = iterator.next();
			String wordToMerged = entry.getValue().word;
			String wordMerged = entry.getKey().word;
			String precessor = wordMerged.substring(0, wordMerged.length()-1);
			String lastChar = wordMerged.substring(wordMerged.length()-1);
			if(nodeMap.containsKey(precessor)) {
				transition.put(precessor + ":" + lastChar, nodeMap.get(wordToMerged));
			}
		}

		FileOutputStream outStr = null;
		BufferedOutputStream buff = null;
		try{
			outStr = new FileOutputStream(new File("/home/sean/IdeaProjects/inference/src/com/tree/DFA.dot"));
			buff = new BufferedOutputStream(outStr);
			buff.write("digraph g {\n".getBytes());
			// drawing nodes
			Iterator<Map.Entry<String, Integer>> nodeIter = nodeMap.entrySet().iterator();
			while(nodeIter.hasNext()) {
				Map.Entry<String, Integer> nodeEntry = nodeIter.next();
				if(finalStates.contains(nodeEntry.getKey())) {
					buff.write((String.valueOf(nodeEntry.getValue()) + "[peripheries=2, label=\"\"]\n").getBytes());
				} else if(nodeEntry.getKey().equals("")){
					buff.write((String.valueOf(nodeEntry.getValue()) + "[label=\"root\"]\n").getBytes());
				} else {
					buff.write((String.valueOf(nodeEntry.getValue()) + "[label=\"\"]\n").getBytes());
				}
			}

			// drawing edges
			Iterator<Map.Entry<String, Integer>> transIter = transition.entrySet().iterator();
			while(transIter.hasNext()) {
				Map.Entry<String, Integer> trans = transIter.next();
				String key = trans.getKey();
				String[] keySplit = key.split(":");
				String preNode = "" + nodeMap.get(keySplit[0]);
				String label = keySplit[1];
				String sucNode = "" + trans.getValue();

				buff.write((preNode + "->" + sucNode + "[label=\"" + label + "\"]\n").getBytes());

			}
			buff.write("}".getBytes());
			buff.flush();
			buff.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				buff.close();
				outStr.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}


	}
}
