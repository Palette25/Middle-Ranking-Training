/**
* DancingBug.java
*/

import info.gridworld.actor.Bug;

public class DancingBug extends Bug{
    //Turn array store
    private int[] turns;
    private int temp;

    //DancingBug Constructer, get input
    public DancingBug(int[] input){
        int len = input.length;
        turns = new int[len];
        System.arraycopy(input, 0, turns, 0, len);
        temp = 0;
    }

    //Override the act function
    public void act(){
        for(int i=0;i<turns[temp];i++){
            //turn for several times
            turn();
        }
        if(canMove()){
            move();
        }
        else {
            turn();
        }
        temp++;
        if(temp == turns.length){
            temp = 0;
        }
    }
}