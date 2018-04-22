/**
* SparseBoundedGrid -- 2.java
*/
import java.util.ArrayList;
import info.gridworld.grid.*;
import java.util.Map;
import java.util.HashMap;

public class SparseBoundedGrid2<E> extends AbstractGrid<E>
{
    /*Store in map */
    private Map<Location, E> map; 
    private int rowNum;
    private int colNum;

    protected SparseBoundedGrid2(){}

    public SparseBoundedGrid2(int rows, int cols){
        if (rows <= 0){
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0){
            throw new IllegalArgumentException("cols <= 0");
        }
        rowNum = rows;
        colNum = cols;
        /*Turn it into hashmap */
        map = new HashMap<Location, E>(); 
    }

    public int getNumRows(){
        return rowNum;
    }

    public int getNumCols(){
        return colNum;
    }

    /*et all occupied Locations*/
    public ArrayList<Location> getOccupiedLocations(){
        ArrayList<Location> result = new ArrayList<Location>();
        for(Location loc : map.keySet()){
            result.add(loc);
        }
        return result;
    }
    /*udge the location is valid or not*/
    public boolean isValid(Location loc){
        boolean rowValid = loc.getRow()>=0&&loc.getRow()<getNumRows();
        boolean colValid = loc.getCol()>=0&&loc.getCol()<getNumCols();
        return rowValid&&colValid;
    }

    public E put(Location loc, E obj){
        if(!isValid(loc)){
            throw new IllegalArgumentException("Location: " + loc.toString() + " is not valid");
        }
        if(obj == null){
            throw new IllegalArgumentException("OBJ is nulll pointer!");
        }
        /*Remove if exist and return the old one, otherwise is null*/
        E oldElement = remove(loc);
        map.put(loc, obj);
        return oldElement;
    }

    public E remove(Location loc){
        if(!isValid(loc)){
            throw new IllegalArgumentException("Location: " + loc.toString() + " is not valid");
        }
        /* First get the element of that location*/
        E result = get(loc); 
        if(result == null){
            return null;
        }
        return map.remove(loc);
    }

    public E get(Location loc){
        if(!isValid(loc)){
            throw new IllegalArgumentException("Location: " + loc.toString() + " is not valid");
        }
        /* Simply return get result*/
        return map.get(loc);
    }

    
}