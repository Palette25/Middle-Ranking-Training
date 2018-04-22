/*
    SpiralBug.java
*/

import info.gridworld.actor.Bug;


public class SpiralBug extends Bug
{
    private int steps;
    private int sideLength;

    public SpiralBug(int length){
        steps = 0;
        sideLength = length;
    }

    /* Move to the next location like a circle */
    public void act(){
        if(steps < sideLength && canMove()){
            move();
            steps++;
        }else {
            turn();
            turn();
            //Adding the sideLength
            sideLength++;
            steps = 0;
        }
    }
}