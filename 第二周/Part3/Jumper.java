/**
* Jumper.java
*/

import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.awt.Color;

public class Jumper extends Actor{

	public Jumper(){
		setColor(Color.RED);
	}

	public Jumper(Color jumperColor){
		setColor(jumperColor);
	}

	public boolean canJump(){
		Grid<Actor> gr = getGrid();
		if(gr == null){
			return false;
		}
		Location loc = getLocation();
		Location next = loc.getAdjacentLocation(getDirection());
		Location nnext = next.getAdjacentLocation(getDirection());
		if (!gr.isValid(nnext))
            return false;
        Actor nneighbor = gr.get(nnext);
        return (nneighbor == null) || (nneighbor instanceof Flower);
	}

	public void jump(){
		Grid<Actor> gr = getGrid();
		if(gr == null){
			return;
		}
		Location loc = getLocation();
		Location next = loc.getAdjacentLocation(getDirection());
		Location nnext = next.getAdjacentLocation(getDirection());
		if(gr.isValid(nnext)){
			moveTo(nnext);
		}else {
			removeSelfFromGrid();
		}
	}

	public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }

	public void act(){
		if(canJump()){
			jump();
		}else {
			turn();
		}
	}
}