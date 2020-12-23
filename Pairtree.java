package assignment4;





/**
This class describes a Peartree class for referencing Pair objects. A Peartree
is a simple binary tree whose data is explicitly of the Pair type, and which
implements the Comparable interface to ensure that tree objects can be
stored/sorted via PriorityQueue, simplifying implementation of the Huffman
Coding algorithm described in lecture.
Most methods are cribbed from the BinaryTree class provided by Srini (Dr.
Srini Sampalli). Used for A4 from previous version.
*/

import java.util.LinkedList;
import java.util.Queue;

public class Pairtree implements Comparable<Pairtree>{
    // declare all required fields
    private Pair data;
	private Pairtree parent;
	private Pairtree left;
	private Pairtree right;

    /**
    Constructor 1
    No arg constructor
    */
    public Pairtree(){
        parent = left = right = null;
		data = null;
    }

    /**
    Constructor 2
    Pair data given
    */
    public Pairtree(Pair p){
        parent = left = right = null;
        data = p;
    }

    public void setData(Pair data){
		this.data = data;
	}

	public void setLeft(Pairtree pt){
		left = pt;
	}

	public void setRight(Pairtree pt){
		right = pt;
	}

	public void setParent(Pairtree pt){
		parent = pt;
	}

	public Pair getData(){
		return data;
	}

	public Pairtree getParent(){
		return parent;
	}

    public Pairtree getLeft(){
		return left;
	}

	public Pairtree getRight(){
		return right;
	}

    public void attachLeft(Pairtree pt){
		if (pt==null)
            return;
		else if (left!=null || pt.getParent()!=null){
			System.out.println("Can't attach");
			return;
		}
		else{
            pt.setParent(this);
            this.setLeft(pt);
		}
	}

	public void attachRight(Pairtree pt){
		if (pt==null)
            return;
		else if (right!=null || pt.getParent()!=null){
			System.out.println("Can't attach");
			return;
		}
		else{
            pt.setParent(this);
            this.setRight(pt);
		}
	}

    // this method simplifies implementation of the Huffman Coding algorithm
    public double getProb(){
        return data.getProb();
    }

    // this method simplifies implementation of the Huffman Coding algorithm
    public char getValue(){
        return data.getValue();
    }

    /**
    The compareTo method overrides the compareTo method of the Comparable
    interface.
    */
    @Override
    public int compareTo(Pairtree pt){
        return Double.compare(this.getProb(), pt.getProb());
    }

    // this method uses a modified BFS to print the data associated with all
    // nodes/trees in level order
    public static void levelorder(Pairtree pt){
        if(pt==null)
            return;
        // initialize Queue to store references to Peartrees and
        // preload agenda
        Queue<Pairtree> q = new LinkedList<Pairtree>();
        q.add(pt);

        while(!q.isEmpty()){
            Pairtree tmp = q.remove();
			System.out.print(tmp.getData() +" ");
            if(tmp.getLeft()!=null)
                q.add(tmp.getLeft());
            if(tmp.getRight()!=null)
                q.add(tmp.getRight());
        }
	}
}
