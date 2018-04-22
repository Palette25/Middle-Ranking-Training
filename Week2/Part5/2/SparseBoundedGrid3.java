/**
* SparseBoundedGrid.java -- 3
*/

import info.gridworld.grid.*;
import java.util.Map;
import java.util.TreeMap;

public class SparseBoundedGrid3<E> extends SparseBoundedGrid2<E>
{
    /*Store in arraylist */
    private Map<Location, E> map; 
    private int rowNum;
    private int colNum;

    public SparseBoundedGrid3(int rows, int cols){
        if (rows <= 0){
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0){
            throw new IllegalArgumentException("cols <= 0");
        }
        map = new TreeMap<Location, E>();
        rowNum = map.size();
        rowNum = rows;
        colNum = cols;
    }

    public int getNumRows(){
        return rowNum;
    }

    public int getNumCols(){
        return colNum;
    }

    /** We don't make any function defines here, cause the grid3 extends grid2, just changing the hashmap
    *   into the treemap, and they have the same methods in a general way.
    */
}