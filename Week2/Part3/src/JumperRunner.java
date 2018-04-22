/**
* JumperRunner.java
*/

import info.gridworld.actor.*;
import info.gridworld.grid.Location;

public final class JumperRunner{
    private JumperRunner(){}

    //JumoerRunner main function
    public static void main(String[] args){
        ActorWorld world = new ActorWorld();
        Jumper pal = new Jumper();
        world.add(new Location(2, 2), pal);
        world.show();
    }
}