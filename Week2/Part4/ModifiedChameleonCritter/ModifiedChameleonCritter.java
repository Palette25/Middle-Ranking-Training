/**
* ModifiedChameleonCritter.java
*/

import info.gridworld.actor.*;
import java.util.ArrayList;
import java.awt.Color;

public class ModifiedChameleonCritter extends ChameleonCritter{
    private static final double DARKENING_FACTOR = 0.05;

    //Modified the getActors() method
    public ArrayList<Actor> getActors(){
        ArrayList<Actor> result = getGrid().getNeighbors(getLocation());
        //If no neighs, get darker
        if(result.size()==0){
            darken();
        }
        return result;
    }

    //Get dark
    public void darken(){
        Color c = getColor();
        int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
        int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
        int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
        setColor(new Color(red, green, blue));
    }
}