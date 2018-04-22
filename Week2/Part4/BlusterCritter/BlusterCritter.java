/**
* BlusterCritter.java
*/
import info.gridworld.actor.Critter;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;

public class BlusterCritter extends Critter{
    private static final double DARKENING_FACTOR = 0.05;
    private int critterNum;

    // BlusterCritter constructer
    public BlusterCritter(int num){
        critterNum = num;
    }

    //Override getActors() method, to get the object number around
    public ArrayList<Actor> getActors(){
        ArrayList<Actor> allIn = new ArrayList<Actor>();
        Location loc = getLocation();
        //Loop to check every actor
        for(int i=loc.getRow()-2;i<=loc.getRow()+2;i++){
            for(int j=loc.getCol()-2;j<=loc.getCol()+2;j++){
                Location tempLoc = new Location(i, j);
                if(getGrid().isValid(tempLoc)&&!tempLoc.equals(loc)){
                    Actor actor = getGrid().get(tempLoc);
                    if(actor!=null){
                        allIn.add(actor);
                    }
                }
            }
        }
        return allIn;
    }

    public void processActors(ArrayList<Actor> actors){
        //Count Critter number
        int cNum = countCritter(actors);
        if(cNum < critterNum){
            brighten();
        }else {
            darken();
        }
    }

    //Count critter
    public int countCritter(ArrayList<Actor> result){
        int count = 0;
        for(Actor a : result){
            if(a instanceof Critter){
                count++;
            }
        }
        return count;
    }

    //Get dark
    public void darken(){
        Color c = getColor();
        int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
        int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
        int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
        setColor(new Color(red, green, blue));
    }

    //Get Bright
    public void brighten(){
        Color c = getColor();
        int red = (int) (c.getRed() * (1 + DARKENING_FACTOR));
        if(red > 255){
            red = 255;
        }
        int green = (int) (c.getGreen() * (1 + DARKENING_FACTOR));
        if(green > 255){
            green = 255;
        }
        int blue = (int) (c.getBlue() * (1 + DARKENING_FACTOR));
        if(blue > 255){
            blue = 255;
        }
        setColor(new Color(red, green, blue));
    }

}