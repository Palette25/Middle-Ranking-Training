/**
* SpiralBugRunner.java
*/

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import java.awt.Color;
/**
* This class runs a world that contains spiral bugs.
* This class is not tested on the AP CS A and AB exams.
*/
public class SpiralBugRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        SpiralBug bob = new SpiralBug(2);
        world.add(new Location(5, 5), bob);
        world.show();
    }
}