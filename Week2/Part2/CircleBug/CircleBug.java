/*
    CircleBug.java
*/

import info.gridworld.actor.Bug;


public class CircleBug extends Bug
{
    private int steps;
    private int sideLength;
    //CircleBug Constructer
    public CircleBug(int length){
        steps = 0;
        sideLength = length;
    }

    /* Move to the next location like a circle */
    public void act(){
        if(steps < sideLength && canMove()){
            move();
            steps++;
        }else {
            //Just turn for once
            turn();
            steps = 0;
        }
    }
}