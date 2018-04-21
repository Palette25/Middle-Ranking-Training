/**
* DancingBug.java
*/

import info.gridworld.actor.Bug;

public class DancingBug extends Bug{
	private int[] turns;
	private int temp;

	public DancingBug(int[] input){
		int len = input.length;
		turns = new int[len];
		for(int i=0;i<len;i++)
			turns[i] = input[i];
		temp = 0;
	}

	public void act(){
		for(int i=0;i<turns[temp];i++)
			turn();
		if(canMove()) move();
		else turn();
		temp++;
		if(temp == turns.length) temp = 0;
	}
}