package mazeBug.info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;


/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	public Location next;
	public boolean isEnd = false;
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	public Stack<Location> currentWay = new Stack<Location>();
	public Integer stepCount = 0; // Count the total steps;
	private boolean hasShown = false;//final message has been shown
	private int[] dirProbability = {1, 1, 1, 1}; // The probability of getting to each directions
						  			// 0 for North, 1 for East, 2 for South, 3 for West, declockwise

	/**
	 * Constructs a maze bug to green color
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		init();
	}

	/*
	* Init the crossLocation stack and probability array value
	*/

	public void init(){
		Location curr = getLocation();
		ArrayList<Location> temp = new ArrayList<Location>();
		temp.add(curr); // Push the first location into the first arrayList
		crossLocation.push(temp); 
		currentWay.push(curr); // Push into the current way stack
		adjustDirPro(); // First adjust the direction probability
	}

	/**
	 * Moves to the next location of the predictions and validations
	 */
	public void act() {
		boolean willMove = canMove();
		if (isEnd == true) {
			//to show step count when reach the goal
			showFinalWay();		
			if (hasShown == false) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			move();
			//increase step count when move 
			stepCount++;
		} else {
			goBack(); // Else go back to old way
		}
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return null;
		ArrayList<Location> valid = new ArrayList<Location>();
		
		// Define only four directions options
		int[] fdirs = {Location.NORTH, Location.EAST, Location.SOUTH, Location.WEST};
		for(int i=0; i<4; i++){
			Location neigh = loc.getAdjacentLocation(fdirs[i]);
			if(gr.isValid(neigh)){
				Actor temp = gr.get(neigh);
				// If the goal is near, then just jump to the goal
				if(isGoal(temp)){
					ArrayList<Location> dest = new ArrayList<Location>();
					next = neigh;
					dest.add(next);
					return dest;
				}else if((temp == null) || (temp instanceof Flower)){
					valid.add(neigh);
				}
			}
		}
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		Location now = getLocation();
		ArrayList<Location> validNeighbors = getValid(now);
		if(validNeighbors.size() != 0){
			return true;
		}else {
			return false;
		}
	}

	/*
	* Go back and check the stack if jump into a dead way
	*/
	public void goBack(){
		if(crossLocation.size() > 0){
			// Pop the old and top way
			crossLocation.pop();
			currentWay.pop();
			if(crossLocation.size() > 0){
				ArrayList<Location> crossList = crossLocation.peek();
				Location newStart = crossList.get(0);
				Location currLoc = getLocation();
				int moveDirc = currLoc.getDirectionToward(newStart);
				if(getGrid().isValid(newStart)){
					setDirection(moveDirc);
					moveTo(newStart);
					stepCount++;
				}else {
					removeSelfFromGrid();
				}
				reduceProbs(moveDirc);
				Flower flower = new Flower(getColor());
				flower.putSelfInGrid(getGrid(), currLoc);
			}
		}
	}

	/*
	* Reduce the probability in array
	*/
	public void reduceProbs(int dir){
		switch(dir){
			case Location.NORTH: dirProbability[2]--;
								 break;
			case Location.EAST:	 dirProbability[3]--;
								 break;
			case Location.SOUTH: dirProbability[0]--;
								 break;
			case Location.WEST:  dirProbability[1]--;
								 break;
			default: return;
		}
	}

	/*
	* Adjust predictions array dirProbability by checking the relative
	* locations between the bug and goal
	*/
	public void adjustDirPro(){
		ArrayList<Location> allLocs = getGrid().getOccupiedLocations();
		Location currLoc = getLocation();
		for(Location loc : allLocs){
			Actor temp = getGrid().get(loc);
			if(isGoal(temp)){
				if(loc.getRow() > currLoc.getRow()){
					dirProbability[2] += 5;
				}else {
					dirProbability[0] += 5;
				}
				if(loc.getCol() > currLoc.getCol()){
					dirProbability[1] += 5;
				}else {
					dirProbability[3] += 5;
				}
			}
		}
	}

	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location loc = getLocation();
		dfsDecision(loc);
		if (gr.isValid(next)) {
			Actor ac = getGrid().get(next);
			if(isGoal(ac)){
				isEnd = true;
			}
			// Set the movement direction
			int currDir = getLocation().getDirectionToward(next);
			moveTo(next);
			setDirection(currDir);
			ArrayList<Location> oldWay = crossLocation.pop();
			ArrayList<Location> newWay = new ArrayList<Location>();
			currentWay.push(next);
			// Pop the top of the stack and add next to it
			oldWay.add(next);
			crossLocation.push(oldWay);
			// Push the new way into stack
			newWay.add(next);
			crossLocation.push(newWay);
		} else
			removeSelfFromGrid();
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}

	/*
	* Show the total steps and way when reach goal
	*/
	public void showFinalWay(){
		for(Location loc : currentWay){
			Actor ac = getGrid().get(loc);
			ac.setColor(Color.GREEN);
		}
	}

	/*
	* Judge whether the actor is goal or not
	*/
	public boolean isGoal(Actor temp){
		return (temp instanceof Rock && temp.getColor().equals(Color.RED));
	}

	/*
	* The depth first search function, make the decision of which location to move
	*/
	public void dfsDecision(Location loc){
		ArrayList<Location> validNeighbors = getValid(loc);
		int maxProb = 0; // The max probability
		int indexOfMaxProb = 0; // The index of the max probability direction
		for(int i=0; i<validNeighbors.size(); i++){
			Location temp = validNeighbors.get(i);
			int direction = loc.getDirectionToward(temp);
			if(dirProbability[direction / 90] > maxProb){
				maxProb = dirProbability[direction / 90];
				indexOfMaxProb = i;
			}
		}

		// Randomly select a location to move
		if(validNeighbors.size() == 0){
			return;
		}else if(validNeighbors.size() == 1){
			next = validNeighbors.get(indexOfMaxProb);
			dirProbability[indexOfMaxProb]++;
		}else {
			randomChoice(validNeighbors, indexOfMaxProb);
		}
	}

	/*
	* Random choose a location relating to the probability array
	*/
	public void randomChoice(ArrayList<Location> valid, int maxIndex){
		int totalProbability = 0;
		for(int i=0; i<4; i++){
			totalProbability += dirProbability[i];
		}
		int randomNum = (int)(Math.random() * totalProbability);
		// Put main possibility on index of max probability
		if(randomNum >= 0 && randomNum < totalProbability * 0.6){
			next = valid.get(maxIndex);
			dirProbability[maxIndex]++;
		}else {
			next = valid.get(randomNum % valid.size());
			int temp = getLocation().getDirectionToward(next);
			dirProbability[temp / 90]++;
		}
	}

}
