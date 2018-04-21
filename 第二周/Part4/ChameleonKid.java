/**
* ChameleonKid.java
*/

import info.gridworld.grid.*;
import info.gridworld.actor.*;
import java.util.ArrayList;

public class ChameleonKid extends ModifiedChameleonCritter{
	public ArrayList<Actor> getActors(){
		ArrayList<Actor> result = getGrid().getNeighbors(getLocation());
		Location front = getLocation().getAdjacentLocation(getDirection());
		Location behind = getLocation().getAdjacentLocation(getDirection()+Location.HALF_CIRCLE);
		if(getGrid().get(front)==null){
			if(getGrid().get(behind)==null){
				Darken();
			}else setColor(getGrid().get(behind).getColor());
		}else {
			if(getGrid().get(behind)==null) setColor(getGrid().get(front).getColor());
			else {
				double ran_num = Math.random();
				if(ran_num>=0.5) setColor(getGrid().get(behind).getColor());
				else setColor(getGrid().get(front).getColor());
			}
		}
		return result;
	}
}