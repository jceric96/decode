package assignment4;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;



public class HuffmanDemo {

	public static void main(String[] args) throws IOException {
		//input reading filename
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter the filename to read from/encode: ");
        String filename = kb.nextLine();
        char[] ch = Huffman.readFile(filename);
        System.out.println("Codes generated. Printing codes to Huffman.txt");
        System.out.println("Printing encoded text to Encoded.txt");
        System.out.println();
        System.out.println("* * * * *");
        System.out.println();
        //Huffman tree building process
        ArrayList<Pair> pairList = addToList(ch);
        Queue s =  Huffman.sortList(pairList);
        BinaryTree<Pair> huffmanTree = Huffman.buildTree(s);
        String[] encode = Huffman.findEncoding(huffmanTree);
        String huffmanCode = codeRef(encode, pairList);
      
        //Create output files     
        //input encode outfile name 
        System.out.println("Enter the Encoded file name: ");
        String encodedFilename = kb.nextLine();
        Huffman.encode(filename, encode, encodedFilename);

        //input Huffman outfile name 
        System.out.println("Enter the filename of document containing Huffman codes: ");
        String HuffmanFilename = kb.nextLine();
        Huffman.writeFile(huffmanCode, HuffmanFilename);
        //print decode content into Decoded.txt
        String decodedFilename = "Decoded.txt";
        Huffman.decode(encodedFilename, encode, decodedFilename);
        System.out.println("Printing decoded text to Decoded.txt");
        
        
	}
	 //Add char[] to ArrayList
	 public static ArrayList<Pair> addToList(char[] ch){
	        ArrayList<Pair> pairlist = new ArrayList<Pair>();
	        pairlist.add(new Pair(ch[0]));
	        //add to ArrayList<Pair>
	        for(int i=1; i<ch.length; i++){
	            boolean inList = false;
	            Pair p = new Pair(ch[i]);
	            for(int j=0; j<pairlist.size(); j++){
	                if(pairlist.get(j).isEqual(p)){
	                    pairlist.get(j).addFreq();
	                    inList = true;
	                    break;
	                }
	            }
	            if(inList == false){
	                pairlist.add(p);
	            }
	        }

	        //set Probability for each Pair
	        for(int i=0; i<pairlist.size(); i++){
	            pairlist.get(i).setProb();
	            //System.out.println(pairlist.get(i).toString());
	        }
	       
	        return pairlist;
	    }
	 //Put Huffman code into String
	 public static String codeRef(String[] encode, ArrayList<Pair> list){
	        String result = "";
	        result += String.format("%-8s%-8s%-12s\n","Symbol", "Prob.", "Huffman Code");
	        for(int i=0; i<encode.length;i++){
	            if(encode[i] != null)
	                result += String.format("%-8s%-8s%-12s\n", (char)i, findProb(list, (char)i) ,encode[i]);
	        }
	        return result;
	    }
	//Find probability for certain char
	 public static double findProb(ArrayList<Pair> list, char key){
	        double result = 0;
	        for(int i=0; i<list.size(); i++){
	            if(list.get(i).getValue() == key)
	                result = list.get(i).getProb();
	        }
	        return result;
	    }
	 
}
