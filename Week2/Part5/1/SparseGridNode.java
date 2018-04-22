/**
* SparseGridNode.java
*/

public class SparseGridNode{
    // For storing the real object
    private Object occupant; 
    // For node column
    private int col; 
    // Next node
    private SparseGridNode next; 

    public SparseGridNode(Object occu, int cols, SparseGridNode nextO){
        occupant = occu;
        col = cols;
        next = nextO;
    }

    public Object getOccupant(){
        return occupant;
    }

    public int getCol(){
        return col;
    }

    public SparseGridNode getNext(){
        return next;
    }

    public void setOccupant(Object another){
        occupant = another;
    }

    public void setNext(SparseGridNode nextO){
        next = nextO;
    }

    public void setCol(int cols){
        col = cols;
    }
}