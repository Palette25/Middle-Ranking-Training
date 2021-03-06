/**
* ZBugRunner.java
*/

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
/**
* This class runs a world that contains z bugs.
* This class is not tested on the AP CS A and AB exams.
*/
public final class ZBugRunner
{
    private ZBugRunner(){}

    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        ZBug bob = new ZBug(5);
        bob.turn();
        bob.turn();
        world.add(new Location(0, 0), bob);
        world.show();
    }
}