/**
* ZBug.java
*/

import info.gridworld.actor.Bug;

public class ZBug extends Bug{
	private int zlength;
	private int steps;
	private int turn_times;

	public ZBug(int len){
		zlength = len;
		steps = 0;
		turn_times = 0;
	}

	public void act(){
		if(turn_times==3 || judge_turn()){
			return;
		}else if(turn_times<=2){
			if(steps == zlength){
				turn_times++;
				if(turn_times==3) return;
				turn();
				turn();
				turn();
				if(turn_times==2){
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

	private boolean judge_turn(){
		if(!canMove()&&steps<zlength) return true;
		else return false;
	}
}