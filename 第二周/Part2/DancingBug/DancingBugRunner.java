/**
* DancingBugRunner.java
*/

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import java.awt.Color;
/**
* This class runs a world that contains dancing bugs.
* This class is not tested on the AP CS A and AB exams.
*/
public class DancingBugRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        int[] array = new int[5];
        for(int i=0;i<5;i++)
        	array[i] = i;
        DancingBug bob = new DancingBug(array);
        world.add(new Location(1, 1), bob);
        world.show();
    }
}