package assignment4;




import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Huffman22 {

    /**
    Code
     provided from previous version and modified for 2020.
    */
    public static void encode()throws IOException{
        // initialize Scanner to capture user input
        Scanner sc = new Scanner(System.in);

        // capture file information from user and read file
        System.out.print("Enter the filename to read from/encode: ");
        String f = sc.nextLine();

        // create File object and build text String
        File file = new File(f);
        Scanner input = new Scanner(file).useDelimiter("\\z");
        String text = input.next();
        
        // close input file
        input.close();

        // initialize Array to hold frequencies (indices correspond to
        // ASCII values)
        int[] freq = new int[256];
        // concatenate/sanitize text String and create character Array
        // nice that \\s also consumes \n and \r
        // we can add the whitespace back in during the encoding phase

        char[] chars = text.replaceAll("\\s", "").toCharArray();
        
        // count character frequencies
        for(char c: chars)
            freq[c]++;

       
        //Your work starts here************************************8


        /*
        for(int i = 0; i<256; i++){
            if(freq[i]!=0){
                // this method of rounding is good enough
                Pair p = new Pair((char)i, Math.round(freq[i]*10000d/chars.length)/10000d);
                pairs.add(p);
            }
        }
        */

       //Apply the huffman algorithm here and build the tree ************************************






        //can be used to get the codes
        //String[] codes = findEncoding(HuffmanTree);




    }


    public static void decode()throws IOException{
        // initialize Scanner to capture user input
        Scanner sc = new Scanner(System.in);

        // capture file information from user and read file
        System.out.print("Enter the filename to read from/decode: ");
        String f = sc.nextLine();

        // create File object and build text String
        File file = new File(f);
        Scanner input = new Scanner(file).useDelimiter("\\Z");
        String text = input.next();
        // ensure all text is consumed, avoiding false positive end of
        // input String
        input.useDelimiter("\\z");
        text += input.next();


        // close input file
        input.close();

        // capture file information from user and read file
        System.out.print("Enter the filename of document containing Huffman codes: ");
        f = sc.nextLine();

        // create File object and build text String
        file = new File(f);
        input = new Scanner(file).useDelimiter("\\Z");
        String codes = input.next();

        // close input file
        input.close();

        //Your work starts here********************************************







    }

    // the findEncoding helper method returns a String Array containing
    // Huffman codes for all characters in the Huffman Tree (characters not
    // present are represented by nulls)
    // this method was provided by Srini (Dr. Srini Sampalli). Two versions are below, one for Pairtree and one for BinaryTree


    private static String[] findEncoding(BinaryTree<Pair> bt){
        String[] result = new String[256];
        findEncoding(bt, result, "");
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

    /*
    private static String[] findEncoding(Peartree pt){
        // initialize String array with indices corresponding to ASCII values
        String[] result = new String[256];
        // first call from wrapper
        findEncoding(pt, result, "");
        return result;
    }

    private static void findEncoding(Peartree pt, String[] a, String prefix){
        // test is node/tree is a leaf
        if (pt.getLeft()==null && pt.getRight()==null){
            a[pt.getValue()] = prefix;
        }
        // recursive calls
        else{
            findEncoding(pt.getLeft(), a, prefix+"0");
            findEncoding(pt.getRight(), a, prefix+"1");
        }
    }
*/

}

