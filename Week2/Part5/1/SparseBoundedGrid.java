/**
* SparseBoundedGrid -- 1.java
*/
import java.util.ArrayList;
import info.gridworld.grid.*;

/*SparseBoundedGrid version1 with ArrayList */
public class SparseBoundedGrid<E> extends AbstractGrid<E>
{
    /*Store in arraylist*/
    private ArrayList<SparseGridNode> gridStoreArray; 
    private int rowNum;
    private int colNum;

    /*Contructor*/
    public SparseBoundedGrid(int rows, int cols){
        if (rows <= 0){
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0){
            throw new IllegalArgumentException("cols <= 0");
        }
        rowNum = rows;
        colNum = cols;
        gridStoreArray = new ArrayList<SparseGridNode>(rows);
        for(int i=0;i<rows;i++){
            SparseGridNode temp = null;
            gridStoreArray.add(temp);
        }
    }

    /*get row num*/
    public int getNumRows(){
        return rowNum;
    }

    /*get column num*/
    public int getNumCols(){
        return colNum;
    }

    public ArrayList<Location> getOccupiedLocations(){
        ArrayList<Location> result = new ArrayList<Location>();
        for(int i=0;i<rowNum;i++){
             /* Get to begin element of the row*/
            SparseGridNode begin = gridStoreArray.get(i);
            while(begin != null){
                Location loc = new Location(i, begin.getCol());
                result.add(loc);
                begin = begin.getNext();
            }
        }
        return result;
    }

    /*Judge whether is valid for the location*/
    public boolean isValid(Location loc){
        boolean colV = loc.getCol()>=0&&loc.getCol()<getNumCols();
        boolean rowV = loc.getRow()>=0&&loc.getRow()<getNumRows();
        return rowV&&colV;
    }

    /*put the element into the grid, in the arraylist*/
    public E put(Location loc, E obj){
        if(!isValid(loc)){
            throw new IllegalArgumentException("Location: " + loc.toString() + " is not valid");
        }
        if(obj == null){
            throw new IllegalArgumentException("OBJ is nulll pointer!");
        }
        /*Remove if exist and return the old one, otherwise is null*/
        E oldElement = remove(loc);
        SparseGridNode begin = gridStoreArray.get(loc.getRow());
        gridStoreArray.set(loc.getRow(), new SparseGridNode(obj, loc.getCol(), begin));
        return oldElement;
    }

    /*Remove the object in the location*/
    public E remove(Location loc){
        if(!isValid(loc)){
            throw new IllegalArgumentException("Location: " + loc.toString() + " is not valid");
        }
        /* First get the element of that location*/
        E result = get(loc); 
        if(result == null){
            return null;
        }
        SparseGridNode begin = gridStoreArray.get(loc.getRow());
        if(begin != null){
            if(begin.getCol() == loc.getCol()) {
                /* Head begin is what we need to remove*/
                gridStoreArray.set(loc.getRow(), begin.getNext());
            }else { 
                /* Loop for the remove element*/
                SparseGridNode front = begin.getNext();
                while(front != null){
                    if(front.getCol() == loc.getCol()){
                        break;
                    }else {
                        begin = begin.getNext();
                        front = front.getNext();
                    }
                }
                if(front != null){
                    begin.setNext(front.getNext());
                }
            }
        }
        return result;
    }

    /*Get the object in the location*/
    public E get(Location loc){
        if(!isValid(loc)){
            throw new IllegalArgumentException("Location: " + loc.toString() + " is not valid");
        }
        SparseGridNode begin = gridStoreArray.get(loc.getRow());
        while(begin != null){
            if(begin.getCol() == loc.getCol()){
                /* return the get occupant obj*/
                return (E)begin.getOccupant();
            }
            begin = begin.getNext();
        }
        return null;
    }

    
}