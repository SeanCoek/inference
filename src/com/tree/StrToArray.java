package com.tree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;
import com.tree.Node;

import javax.swing.text.DefaultEditorKit.InsertBreakAction;

/**
 * 从文件中读取的字符串转换为字符数组
 *
 */
public class StrToArray {

	public static void main(String[] args) {
		String str0,str1;
		String[] initArray=null;
		List<String> initList=readTxtFileIntoStringArrList("/home/sean/IdeaProjects/inference/src/com/tree/test.txt");
		String[] strArray0 = null;
		String[] strArray1 = null;
//		TrieTree PTMM = new TrieTree();
		
		initArray = initList.toArray(new String[]{});
		//不接受状态 0
		str0 = initArray[1];
		//接受状态 1
		str1 = initArray[0];
		System.out.println("调用convertStrToArray结果：");
		strArray0 = convertStrToArray(str0);
		printArray(strArray0);
		strArray1 = convertStrToArray(str1);
		printArray(strArray1);
		
//		TrieTree tree = generateTree(strArray0,strArray1);
//		MainAlgorithm m = new MainAlgorithm();
//		m.mainAlgorithm(tree);
		Node root = Node.generateTree(strArray0,strArray1);
		Node.display(root);
		
	}
	
	//生成树
	public static TrieTree generateTree(String[] arr0,String[] arr1){
		int len0 = arr0.length;
		int len1 = arr1.length;
		TrieTree tempTree = new TrieTree();
		for(int i=0;i<len0;i++){
			tempTree.insert(arr0[i],0);
		}
		for(int i=0;i<len1;i++){
			tempTree.insert(arr1[i],1);
		}
		return tempTree;
	}
	//使用String的split 方法
	public static String[] convertStrToArray(String str){
		String[] strArray = null;
		strArray = str.split(",");
		return strArray;
	}
	
	
	public static void printArray(String[] arr){
		int len = arr.length;
		for(int i=0;i<len;i++){
			System.out.println(arr[i]);
		}
	}
	
	public static List<String> readTxtFileIntoStringArrList(String filePath)
    {
        List<String> list = new ArrayList<String>();
        try{
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists()){ 
            	// 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                while ((lineTxt = bufferedReader.readLine()) != null){
                    list.add(lineTxt);
                }
                bufferedReader.close();
                read.close();
            }
            else{
                System.out.println("找不到指定的文件");
            }
        }
        catch (Exception e){
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

        return list;
    }
}
