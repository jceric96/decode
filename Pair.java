package assignment4;

import java.text.DecimalFormat;

/**
This class describes a simple Pair class for storing character/double pairs. Used for A4 from previous version.
*/

public class Pair implements Comparable<Pair>{
    // declare all required fields
	DecimalFormat df2 = new DecimalFormat("###.####");
    private char value;
    private double prob;
    public int freq;
    /**
    Constructor 1
    All args accounted for
    */
    public Pair(char c){
        value = c;
        freq = 1;
    }
    public Pair(char v, double p){
        value = v;
        prob = p;
    }

    public void setValue(char v){
        value = v;
    }

    public void setProb(){
        prob = Double.valueOf(df2.format(freq/455.0));
    }
    public void setFreq(int n){
        freq = n;
    }
    public char getValue(){
        return value;
    }

    public double getProb(){
        return prob;
    }
    public int getFreq(){
        return freq;
    }
    
    public boolean isEqual(Pair p){
       return p.getValue()==(getValue());
    }
    public void addFreq(){
        setFreq(getFreq() + 1);
    }
    /**
    The compareTo method overrides the compareTo method of the Comparable
    interface.
    */
    @Override
    
    public int compareTo(Pair p){
        return Double.compare(this.getProb(), p.getProb());
    }

    /**
    The toString method overrides the toString method of the Object class.
    */
    @Override
    public String toString(){
        return value+" : "+prob;
    }
    
   
}
