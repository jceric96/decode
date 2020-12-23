package assignment4;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Huffman {
	    //Encode the file and write it into output file
	    public static void encode(String inputFileName, String[] encode, String outputFileName) throws IOException{      
	    	String s = "";
	        char[] ch = readFileSpace(inputFileName);
	        //for  loop to change the char[] elements to String
	        for(int i=0; i<ch.length;i++){
	            if(ch[i] == ' '){
	                s += ' ';
	            }
	            else if(ch[i] == '\n'){
	                s += '\n';
	            }
	            for(int j=0; j<encode.length;j++){
	                if(encode[j] != null && (char)j == ch[i]){
	                    s += encode[j];
	                }
	            }
	        }
	        //write content into output file
	        writeFile(s, outputFileName);
	       
	    }
	    //Sort and add values in pairlist, 
	    //and create BinaryTree add into Queue s
	    public static Queue sortList(ArrayList<Pair> list){
	    	//create a queue to store list's elements
	    	Queue<BinaryTree<Pair>> s = new LinkedList<BinaryTree<Pair>>();
	        Collections.sort(list);
	        //for loop to add list elements into Queue s
	        for(int i=0; i<list.size(); i++){
	            BinaryTree<Pair> bp = new BinaryTree<Pair>();
	            bp.makeRoot(list.get(i));
	            s.add(bp);
	        }
	        return s;
	    }
	    //Build the Huffman tree
	    public static BinaryTree<Pair> buildTree(Queue<BinaryTree<Pair>> s){
	    	// create Queue t to Stroe  Binary Tree
	    	Queue<BinaryTree<Pair>> t = new LinkedList<BinaryTree<Pair>>();
	    	// while loop to get elements from Queue s
	    	// and use those elements to build Binary Tree
	        while(!s.isEmpty()){
	        	//create two Binary tree nodes
	            BinaryTree<Pair> a = new BinaryTree<Pair>();
	            BinaryTree<Pair> b = new BinaryTree<Pair>();
	            //get elements from  Queue s
	            a = s.poll();
	            b = s.poll();	         
	            //get the prob value of a and b
	            //and add them 
	            double rootProb = a.getData().getProb() + b.getData().getProb();
	            Pair root = new Pair('0', rootProb);
	            //create a binary tree node as a and b parent node
	            BinaryTree<Pair> p = new BinaryTree<Pair>();
	            p.makeRoot(root);
	            p.attachLeft(a);
	            p.attachRight(b);
	            //add this binary tree into Queue t
	            t.add(p);
	        }
	        //while loop to get small binary tree from Queue t 
	        //to complete a bigger binary tree 
	        while(t.size()>1){
	        	//create two Binary tree nodes
	            BinaryTree<Pair> a = new BinaryTree<Pair>();
	            BinaryTree<Pair> b = new BinaryTree<Pair>();
	            //get elements from  Queue t
	            a = t.poll();
	            b = t.poll();
	            //get the prob value of a and b
	            //and add them 
	            double rootProb = a.getData().getProb() + b.getData().getProb();
	            Pair root = new Pair('0', rootProb);
	            //create a binary tree node as a and b parent node
	            BinaryTree<Pair> p = new BinaryTree<Pair>();
	            p.makeRoot(root);
	            p.attachLeft(a);
	            p.attachRight(b);
	            t.add(p);
	        }
	        //output binary tree
	        return t.element();
	    }
	    //Encode the file and write it into output file
	    public static void decode(String inputFileName, String[] encode, String outputFileName) throws IOException{
	        
	    	String s = "";
	        char[] ch = readFileSpace(inputFileName);
	        String binary = "";
	        //for  loop to change the ch[] element to String
	        for(int i=0; i<ch.length; i++){
	            if(ch[i] == ' '){
	                s += ' ';
	            }
	            else if(ch[i] == '\n'){
	                s += '\n';
	            }
	            else binary = binary + Character.toString(ch[i]);
	            if(binary.length() >= 4)
	                for(int j=0; j<encode.length;j++){
	                    if(encode[j] != null && encode[j].equals(binary)){
	                        s += (char)j;
	                        binary = "";
	                    }
	                 }
	        }
	        //write content into output file
	        writeFile(s, outputFileName);
	     }
	     
	     //Read file convert into char[]
	     public static char[] readFile(String filename) throws IOException{
	    	//input filename
	        File file = new File(filename);
	        String str = new Scanner(file).useDelimiter("\\A").next();
	        str = str.replace(" ", "");
	        str = str.replace("\n", "");
	        //convert String content into char[] 
	        char[] ch = str.toCharArray();
	        return ch;
	     }
	     
	     //Write String into file
	     public static void writeFile(String s, String filename) throws IOException{
	        //input outFile name
	    	PrintWriter outFile = new PrintWriter(filename);
	        outFile.format(s);
	        outFile.close();       
	     }
	     
	     //Read file convert into char[] with Space
	     public static char[] readFileSpace(String filename) throws IOException{
	    	//input reading filename
	        File file = new File(filename);
	        String str = new Scanner(file).useDelimiter("\\A").next();
	        char[] ch = str.toCharArray();
	        return ch;
	     }
	     
	     
	     public static void findEncoding(BinaryTree<Pair> t, String[] a, String prefix){
	        if (t.getLeft()==null&& t.getRight()==null){
	            a[(byte)(t.getData().getValue())]= prefix;
	        }
	        else{
	            findEncoding(t.getLeft(), a, prefix+"0");
	            findEncoding(t.getRight(), a, prefix+"1");
	        }
	     }
	     
	     public static String[] findEncoding(BinaryTree<Pair> t){
	        String[] result = new String[256];
	        findEncoding(t, result, "");
	        return result;
	     }
}
