/**
* KingCrab.java
*/

public class KingCrab extends CrabCritter{
	public void processActors(ArrayList<Actor> actors){
		for(Actor a : actors){
			if(a.canMove()){
				a.move();
			}else {
				a.removeSelfFromFrid();
			}
		}
	}
}