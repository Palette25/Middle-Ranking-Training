/**
* ZBug.java
*/

import info.gridworld.actor.Bug;

public class ZBug extends Bug{
    private int zlength;
    private int steps;
    //For recording the time
    private int turnTimes;

    public ZBug(int len){
        zlength = len;
        steps = 0;
        turnTimes = 0;
    }

    //Override the act function
    public void act(){
        if(turnTimes==3 || (!canMove()&&steps<zlength)){
            return;
        }else if(turnTimes<=2){
            //Turn to complete the Z
            if(steps == zlength){
                turnTimes++;
                if(turnTimes==3) {
                    return;
                }
                /* turn to set direction */
                turn();
                turn();
                turn();
                if(turnTimes==2){
                    turn();
                    turn();
                }
                steps = 0;
            }else {
                steps++;
                move();
            }
        }
    }

}