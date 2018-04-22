/**
* Jumper.java
*/

import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.awt.Color;

public class Jumper extends Actor{

    //Jumper Contructer
    public Jumper(){
        setColor(Color.RED);
    }

    //Constructer with parameter
    public Jumper(Color jumperColor){
        setColor(jumperColor);
    }

    //Define whether it can jump
    public boolean canJump(){
        Grid<Actor> gr = getGrid();
        if(gr == null){
            return false;
        }
        //Get the adjacentlocation in the next, and the next-next
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location nnext = next.getAdjacentLocation(getDirection());
        if (!gr.isValid(nnext)){
            return false;
        }
        Actor nneighbor = gr.get(nnext);
        return (nneighbor == null) || (nneighbor instanceof Flower);
    }

    //Jump to the next two location if valid
    public void jump(){
        Grid<Actor> gr = getGrid();
        if(gr == null){
            return;
        }
        //Get nextlocation in direction, including the next-next location
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location nnext = next.getAdjacentLocation(getDirection());
        if(gr.isValid(nnext)){
            moveTo(nnext);
        }else {
            removeSelfFromGrid();
        }
    }

    //Turn direction when face others rock or objects
    public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }

    //Override the act method, change it to jump
    public void act(){
        if(canJump()){
            jump();
        }else {
            turn();
        }
    }
}