/**
* BlusterCritter.java
*/

public class BlusterCritter extends Critter{
	private static final DARKENING_FACTOR = 0.05;
	private int critter_num;

	public BlusterCritter(int num){
		critter_num = num;
	}

	public ArrayList<Actor> getActors(){
		ArrayLIst<Actor> oneStep = getGrid().getNeighbors(getLocation());
		ArrayList<Actor> allIn = new ArrayList<Actor>();
		for(Actor a : oneStep){
			allIn.Add(a);
			for(Actor b : getGrid().getNeighbors(a.getLocation())){
				allIn.Add(b);
			}
		}
		int c_num = countCritter(allIn);
		if(c_num < critter_num){
			Brighten();
		}else {
			Darken();
		}
		return oneStep;
	}

	public int countCritter(ArrayList<Actor> result){
		int count = 0;
		for(Actor a : result){
			if(a instanceof Critter){
				count++;
			}
		}
		return count;
	}

	public void Darken(){
		Color c = getColor();
        int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
        int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
        int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
        setColor(new Color(red, green, blue));
	}

	public void Brighten(){
		Color c = getColor();
        int red = (int) (c.getRed() * (1 + DARKENING_FACTOR));
        int green = (int) (c.getGreen() * (1 + DARKENING_FACTOR));
        int blue = (int) (c.getBlue() * (1 + DARKENING_FACTOR));
        setColor(new Color(red, green, blue));
	}

}