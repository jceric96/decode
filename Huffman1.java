package assignment4;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;

import practice4.BinaryTree;
import practice4.Pair;

public class Huffman1 {
	 public static void encode()throws IOException{
		 Scanner sc = new Scanner(System.in);
		 System.out.print("Enter the filename to read from/encode: ");
		 String f = sc.nextLine();
		 File file = new File(f);
	     Scanner input = new Scanner(file).useDelimiter("\\z");
	     String text = input.next();
	     
	     input.close();
	     int[] freq = new int[256];
	     char[] chars = text.replaceAll("\\s", "").toCharArray();
	     //System.out.println(chars);
	     for(char c: chars)
	            freq[c]++;
	     ArrayList<Pair> pairs = new ArrayList<Pair>();
	     for(int i = 0; i<256; i++){
	            if(freq[i]!=0){
	                // this method of rounding is good enough
	                Pair p = new Pair((char)i, Math.round(freq[i]*10000d/chars.length)/10000d);
	                pairs.add(p);
	            }
	        }
	     Collections.sort(pairs);
	     //System.out.println(pairs);
	     Queue<BinaryTree<Pair>> S = new LinkedList<BinaryTree<Pair>>();
	    
	     for(int i=0; i<pairs.size(); i++){   
	    	 BinaryTree<Pair> bt = new BinaryTree<Pair>();
	    	 Pair n =pairs.get(i);
		     bt.makeRoot(n);
		     System.out.println(bt.getData());
		     S.add(bt);	
		     //System.out.println(S.element().getData().getProb());
		     bt=null;
		    
	     }	
	     //System.out.println(S.element().getData().getProb());
	     Queue<BinaryTree<Pair>> T = new LinkedList<BinaryTree<Pair>>();
	     BinaryTree<Pair> A = new BinaryTree<Pair>();
         BinaryTree<Pair> B = new BinaryTree<Pair>();
	     while(!S.isEmpty()){
	    	 A= S.poll();
	    	 B= S.poll();
	    	 double rootProb=A.getData().getProb()+B.getData().getProb();
	    	// DecimalFormat df = new DecimalFormat("0.0000");
	 	    // System.out.println(df.format(rootProb));   
	    	 Pair root = new Pair('0', rootProb);
	    	// System.out.println(root);
	    	 BinaryTree<Pair> p = new BinaryTree<Pair>();
	            p.makeRoot(root);
	            p.attachLeft(A);
	            p.attachRight(B);
	            T.add(p);
	     }
	     while(T.size()>1){
	         A=T.poll();
	         B=T.poll();
	         double rootProb=A.getData().getProb()+B.getData().getProb();
	         Pair root = new Pair('0', rootProb);
	         BinaryTree<Pair> p = new BinaryTree<Pair>();
	            p.makeRoot(root);
	            p.attachLeft(A);
	            p.attachRight(B);
	            T.add(p);          
	            }
         System.out.println(T.element().getData());  
         String[] codes= findEncoding(T.element());
         System.out.println(codes);  
         String result ="";
         result += String.format("%-8s%-8s%-12s\n","Symbol", "Prob.", "Huffman Code");
         //for(int i=0; i<codes.length;i++){
        	// if(codes[i] != null)
        	//	 result += String.format("%-8s%-8s%-12s\n", (char)i, pairs.get(i).getProb() ,codes[i]);     	 
         //}
         System.out.println(result);  
	 }
	 public static void decode()throws IOException{
		 Scanner sc = new Scanner(System.in);
		 System.out.print("Enter the filename to read from/decode: ");
	     String f = sc.nextLine();
	     File file = new File(f);
	     Scanner input = new Scanner(file).useDelimiter("\\Z");
	     String text = input.next();
	     input.useDelimiter("\\z");
	     text += input.next();
	     input.close();
	     System.out.print("Enter the filename of document containing Huffman codes: ");
	     f = sc.nextLine();
	     file = new File(f);
	     input = new Scanner(file).useDelimiter("\\Z");
	     String codes = input.next();
	     input.close();
	 }
	 private static String[] findEncoding(BinaryTree<Pair> bt){
	        String[] result = new String[256];
	        findEncoding(bt, result, "");
	        return result;
	    }
	 public static double findProb(ArrayList<Pair> list, char key){
	        double result = 0;
	        for(int i=0; i<list.size(); i++){
	            if(list.get(i).getValue() == key)
	                result = list.get(i).getProb();
	        }
	        return result;
	    }

	 private static void findEncoding(BinaryTree<Pair> bt, String[] a, String prefix){
	        // test is node/tree is a leaf
	        if (bt.getLeft()==null && bt.getRight()==null){
	            a[bt.getData().getValue()] = prefix;
	        }
	        // recursive calls
	        else{
	            findEncoding(bt.getLeft(), a, prefix+"0");
	            findEncoding(bt.getRight(), a, prefix+"1");
	        }
	    }
	 
}
