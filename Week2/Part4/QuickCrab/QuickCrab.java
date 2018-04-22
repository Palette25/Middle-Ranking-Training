/**
* QuickCrab.java
*/

import info.gridworld.grid.Location;
import java.util.ArrayList;

public class QuickCrab extends CrabCritter{
    //Override the getMoveLocations() method
    public ArrayList<Location> getMoveLocations(){
        //Storing the one step locations
        ArrayList<Location> locs = new ArrayList<Location>();
        //Storing the two steps locations
        ArrayList<Location> twoLoc = new ArrayList<Location>();
        int dirc = 0;
        //Left and right directions array
        int[] dirs = {Location.LEFT, Location.RIGHT};
        int rightDir = Location.RIGHT;
        int leftDir = Location.LEFT;
        for(Location loc : getLocationsInDirections(dirs)){
            Object neigh = null;
            if(getGrid().isValid(loc)) {
                neigh = getGrid().get(loc);
            }else {
                // SIimply use error string to present
                neigh = "Error: Invalid"; 
            }
            if(neigh == null){
                //Get whether right or lrft
                int temp = dirc==1?rightDir:leftDir;
                Location nnloc = loc.getAdjacentLocation(getDirection()+temp);
                Object nneigh = null;
                if(getGrid().isValid(nnloc)) {
                    //Just get the next next location
                    nneigh = getGrid().get(nnloc);
                }else {
                    //Set to the error mess
                    nneigh = "Error: Invalid";
                }
                if(nneigh == null) {
                    twoLoc.add(nnloc);
                }else {
                    locs.add(loc);
                }
            }
            dirc++;
        }
        //Check whether the twoLength away locations are valid or not
        if(twoLoc.size() == 0){
            return locs;
        }else {
            return twoLoc;
        }
    }

}