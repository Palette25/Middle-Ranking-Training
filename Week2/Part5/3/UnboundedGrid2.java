
import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.util.ArrayList;

import java.util.*;

/**
* UnboundedGrid version2, with enlargeing method
**/
public class UnboundedGrid2<E> extends AbstractGrid<E>
{
    private Object[][] gridStoreArray;
    /* This level for the enlarge time record */
    private int largeLevel; 

    /**
    * UnboundedGrid2 constructer
    */
    public UnboundedGrid2()
    {
        /* Initialize, with level=1, size=16 */
        gridStoreArray = new Object[16][16]; 
        largeLevel = 1;
    }

    public int getNumRows()
    {
        return -1;
    }

    public int getNumCols()
    {
        return -1;
    }

    /**
    * Judge whether the location is valid or not
    */
    public boolean isValid(Location loc)
    {
        boolean rowValid = loc.getRow()>=0;
        boolean colValid = loc.getCol()>=0;
        return rowValid&&colValid;
    }

    /* get location occupied in object array form*/
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> a = new ArrayList<Location>();
        for(int i=0;i<largeLevel*16;i++){
            for(int j=0;j<largeLevel*16;j++){
                Location loc = new Location(i, j);
                if(get(loc) != null){
                    a.add(loc);
                }
            }
        }
        return a;
    }

    public E get(Location loc)
    {
        if (loc == null){
            throw new IllegalArgumentException("loc == null");
        }
        if(!checkLocValid(loc)){
            return null;
        }
        return (E)gridStoreArray[loc.getRow()][loc.getCol()];
    }

    public E put(Location loc, E obj)
    {
        if (loc == null){
            throw new IllegalArgumentException("loc == null");
        }
        if (obj == null){
            throw new IllegalArgumentException("obj == null");
        }
        if(loc.getRow() >= largeLevel*16 || loc.getCol() >= largeLevel*16){
            /* use enlargesize method to enlarge the size */
            enlargeSize(); 
        }
        E oldObj = get(loc);
        gridStoreArray[loc.getRow()][loc.getCol()] = obj;
        return oldObj;
    }

    public E remove(Location loc)
    {
        if (loc == null){
            throw new IllegalArgumentException("loc == null");
        }
        if(loc.getRow() >= largeLevel*16 || loc.getCol() >= largeLevel*16){
            return null;
        }
        E oldObj = get(loc);
        gridStoreArray[loc.getRow()][loc.getCol()] = null;
        return oldObj;
    }

    public boolean checkLocValid(Location loc){
        if(loc.getRow()<0||loc.getRow()>=largeLevel*16){
            return false;
        }
        if(loc.getCol()<0||loc.getCol()>=largeLevel*16){
            return false;
        }
        return true;
    }

    public void enlargeSize(){
        Object[][] temp = new Object[largeLevel*16][largeLevel*16];
        for(int i=0;i<largeLevel*16;i++){
            System.arraycopy(gridStoreArray[i], 0, temp[i], 0, largeLevel*16);
        }
        /* the level multiply 2, and make level up,size larger*/
        largeLevel *= 2; 
        gridStoreArray = new Object[largeLevel*16][largeLevel*16];
        for(int i=0;i<(largeLevel-1)*16;i++){
            System.arraycopy(temp[i], 0, gridStoreArray[i], 0, (largeLevel-1)*16);
        }
    }
}
