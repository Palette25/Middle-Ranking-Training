/**
* RockHound.java
*/
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import java.util.ArrayList;

public class RockHound extends Critter{
    //Override processActors() method
    public void processActors(ArrayList<Actor> actors){
        for (Actor a : actors)
        {
            //Remove if it's not a Critter
            if (!(a instanceof Critter)){
                a.removeSelfFromGrid();
            }
        }
    }
}