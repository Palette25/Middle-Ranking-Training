/**
* ModifiedChameleonCritter.java
*/

public class ModifiedChameleonCritter extends ChameleonCritter{
	private static final double DARKENING_FACTOR = 0.05;

	public ArrayList<Actor> getActors(){
		ArrayList<Actor> result = getGrid().getNeighbors(getLocation());
		if(result.size()==0){
			Darken();
		}
		return result;
	}
	public void Darken(){
		Color c = getColor();
        int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
        int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
        int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
        setColor(new Color(red, green, blue));
	}
}