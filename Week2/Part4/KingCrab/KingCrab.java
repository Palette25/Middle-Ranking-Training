/**
* KingCrab.java
*/
import info.gridworld.grid.Location;
import info.gridworld.actor.*;
import info.gridworld.grid.Grid;
import java.util.ArrayList;

public class KingCrab extends CrabCritter{
    //Override processActors, to make them out for one location
    public void processActors(ArrayList<Actor> actors){
        Grid<Actor> gr = getGrid();
        for(Actor a : actors){
            //Set direction for this actor
            a.setDirection(a.getLocation().getDirectionToward(getLocation()) + Location.HALF_CIRCLE);
            Location loc = a.getLocation();
            //Get the next and next location
            Location next = loc.getAdjacentLocation(a.getDirection());
            if (gr.isValid(next)){
                //Move to is valid
                a.moveTo(next);
            }
            else{
                //else just remove itself
                a.removeSelfFromGrid();
            }
        }
    }

}