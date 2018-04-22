/**
* ChameleonKid.java
*/

import info.gridworld.grid.*;
import info.gridworld.actor.*;
import java.util.ArrayList;

public class ChameleonKid extends ModifiedChameleonCritter{
    //Define the error message
    private String errorStr = "Error: Invalid";

    public void processActors(ArrayList<Actor> actors)
    {
        Location front = getLocation().getAdjacentLocation(getDirection());
        Location behind = getLocation().getAdjacentLocation(getDirection()+Location.HALF_CIRCLE);
        //Check whether valid of these two locations
        boolean fValid = getGrid().isValid(front), bValid = getGrid().isValid(behind);
        Object frontObj = fValid?getGrid().get(front):errorStr;
        Object behindObj = bValid?getGrid().get(behind):errorStr;
        //Start examining
        if(frontObj == null){
            if(behindObj == null || behindObj.equals(errorStr)){
                darken();
            }else {
                setColor(getGrid().get(behind).getColor());
            }
        }else if(!frontObj.equals(errorStr)){
            //Next stituation
            if(behindObj == null || behindObj.equals(errorStr)) {
                //Set color if in this condition
                setColor(getGrid().get(front).getColor());
            }
            else {
                double ranNum = Math.random();
                if(ranNum>=0.5) {
                    setColor(getGrid().get(behind).getColor());
                }
                else {
                    setColor(getGrid().get(front).getColor());
                }
            }
        }else {
            darken();
        }
    }
}