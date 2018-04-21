/**
* JumperRunner.java
*/

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import java.awt.Color;

public class JumperRunner{
	public static void main(String[] args){
		ActorWorld world = new ActorWorld();
		Jumper pal = new Jumper();
		world.add(new Location(2, 2), pal);
		world.show();
	}
}