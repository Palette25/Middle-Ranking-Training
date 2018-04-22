## Part3简答题

1.  int row_num = loc1.getRow();

   * The getRow() method in Location class

     ```java
     //@file: info/gridworld/grid/Location.java
     //@line: 110~113
     public int getRow()
         {
             return row;
         }

     ```

2. The value of b after execting is false, because location in row 4, col 3 is different from location in row 3, col 4.

   * The equals() method in Location class

     ```java
     //@file: info/gridworld/grid/Location.java
     //@line: 205~212
     public boolean equals(Object other)
         {
             if (!(other instanceof Location))
                 return false;

             Location otherLoc = (Location) other;
             return getRow() == otherLoc.getRow() && getCol() == otherLoc.getCol();
         }
     ```

3. The value of loc3 is Location(4, 4), because the direction of the getAdjacentLocation() method is the south, so the next location will be the location that below loc2.

   * The getAdjacentLocation() method in Location class

     ```java
     //@file: info/gridworld/grid/Location.java
     //@line: 130~169
     public Location getAdjacentLocation(int direction)
         {
             ...
             int dc = 0;
             int dr = 0;
             ...
             if (adjustedDirection == SOUTH)
                 dr = 1;
             ...
             return new Location(getRow() + dr, getCol() + dc);
         }
     ```

4.  The value of dir is 135(degrees), that is the SOUTHEAST direction, and in integer we use 135 to present it.

   * The getDirectionToward() method in Location class

     ```java
     //@file: info/gridworld/grid/Location.java
     //@line: 178~195
     public int getDirectionToward(Location target)
         {
             int dx = target.getCol() - getCol();
             int dy = target.getRow() - getRow();
             int angle = (int) Math.toDegrees(Math.atan2(-dy, dx));
             int compassAngle = RIGHT - angle;
             
             compassAngle += HALF_RIGHT / 2;
             
             if (compassAngle < 0)
                 compassAngle += FULL_CIRCLE;
             
             return (compassAngle / HALF_RIGHT) * HALF_RIGHT;
         }
     ```

5.  The getAdjacentLocation() method get the direction as parameter, and use this direction to find the adjacent location in the given direction that is cloest to the location of its own, and return it.If the input direction is not the mutiple of 45, then we change it to the nearest degree which is the mutiple of 45.

   * The getAdjacentLocation() method in Location class

     ```java
     //@file: info/gridworld/grid/Location.java
     //@line: 133~137
     int adjustedDirection = (direction + HALF_RIGHT / 2) % FULL_CIRCLE;
             if (adjustedDirection < 0)
                 adjustedDirection += FULL_CIRCLE;

     adjustedDirection = (adjustedDirection / HALF_RIGHT) * HALF_RIGHT;
     ```

6.  We take an instance of Grid named gr as an example: The number of occupied locations in grid can be calculated as gr.getOccupiedLocations().size(), and in contrary, we have gr2 as an instance of BoundedGrid, and the number of empty locations in gr2 is gr2.getNumRows() * gr2.getNumCols() - gr2.getOccupiedLocations().size().

   * The getOccupiedLocations() method in Grid class

     ```java
     //@file: info/gridworld/grid/Grid.java
     //@line: 85
     ArrayList<Location> getOccupiedLocations();
     ```

   * The override getOccupiedLocation() method in BoundedGrid class

     ```java
     //@file: info/gridworld/grid/BoundedGrid.java
     //@line: 66~83
     public ArrayList<Location> getOccupiedLocations()
         {
             ArrayList<Location> theLocations = new ArrayList<Location>();
             // Look at all grid locations.
             for (int r = 0; r < getNumRows(); r++)
             {
                 for (int c = 0; c < getNumCols(); c++)
                 {
                     // If there's an object at this location, put it in the array.
                     Location loc = new Location(r, c);
                     if (get(loc) != null)
                         theLocations.add(loc);
                 }
             }
             return theLocations;
         }
     ```

7.  We can use the isValid() method of the grid, and use boolean result = gr.isValid(new Location(10, 10)). If the result is true, then the location is valid and is in the grid, otherwise is not valid. 

   * isValid() method of the Grid class

     ```java
     //@file: info/gridworld/grid/Grid.java
     //@line: 50
     boolean isValid(Location loc);
     ```

8. Because the Grid class is designed to be an interface, and it defines the methods that other classes need to implement.We can find the implementations of the methods in BoundedGrid, UnboundedGrid and AbstractGrid class, and AbstractGrid class only implements some methods of the Grid, so it's defined as an abstract class, and its child classes includes BoundedGrid and UnboundedGrid, they implements the rest of methods of the Grid interface.

   * The relations of Grid, AbstractGrid, BoundedGrid and UnboundedGrid

     ```java
     //@file: info/gridworld/grid/Grid.java
     //@line: 29
     public interface Grid<E>

     //@file: info/gridworld/grid/AbstractGrid.java
     //@line: 26
     public abstract class AbstractGrid<E> implements Grid<E>

     //@file: info/gridworld/grid/BoundedGrid.java
     //@line: 29
     public class BoundedGrid<E> extends AbstractGrid<E>

     //@file: info/gridworld/grid/UnboundedGrid.java
     //@line: 31
     public class UnboundedGrid<E> extends AbstractGrid<E>
     ```

9. On the one hand, as the user that simply use APIs of Grid or other class, we always think that obj[i] pattern is more simple than obj.get(i), and in this way for user benifit, we can change the ArrayList to the array.

   On the other hand, as the designer of the Grid, an ArrayList is greater than array, because ArrayList does not require us to know the size before constructing it, but the array needs the size.If the class does not record the number of objects, then the ArrayList is more useful than array.

10. An Actor has a location, a direction and a color, and it also has a reference to the Grid.

    * Define in the Actor class

      ```java
      //@file: info/gridworld/actor/Actor.java
      //@line: 29~34
      public class Actor
      {
          private Grid<Actor> grid;
          private Location location;
          private int direction;
          private Color color;
      ```

11. When an Actor is constructed, its initial color is blue and direction is NORTH.

    * Define in the constructer of Actor class

      ```java
      //@file: info/gridworld/actor/Actor.java
      //@line: 39~42
      public Actor()
          {
              color = Color.BLUE;
              direction = Location.NORTH;
      ```

12.  An actor has its own properties and states, and these variables need to be stored and changed in methods belonging to the Actor class, but an interface does not allow us to define instance variables and implement methods, so we must choose Actor to be a class.

    * Instance variables in Actor class

      ```java
      //@file: info/gridworld/actor/Actor.java
      //@line: 29~34
      public class Actor
      {
          private Grid<Actor> grid;
          private Location location;
          private int direction;
          private Color color;
      ```

13. 1. No, if the actor is in the grid, then it just cannot put itself back in the grid again, and if you try to do this, then it will throw an IllegalStateException, because when you put youself again, the removeSelfFromGrid method will detect whether the actor in location is yourself or not, if not it will have excepion.

      * The putSelfInGrid method in Actor class

        ```java
        //@file: info/gridworld/actor/Actor.java
        //@line: 121~123
         Actor actor = gr.get(loc);
         if (actor != null)
             actor.removeSelfFromGrid();
        ```

      * The removeSelfFromGrid method in Actor class

        ```java
        //@file: info/gridworld/actor/Actor.java
        //@line: 138~141
        if (grid.get(location) != this)
            throw new IllegalStateException(
                   "The grid contains a different actor at location "
                        + location + ".");
        ```

    2. No, if the actor has been removed from the grid then we just cannot remove it again, this will also cause the IllegalStateExceptioin because once you remove the actor, the element in location will be null, which is exactly different from the actor itself, the supporting is above.

    3. Yes, if the actor is put in the grid, and then we can remove it, and put it into the grid again.

        * The removeSelfFromGrid method in Actor class, normally remove if valid

          ```java
          //@file: info/gridworld/actor/Actor.java
          //@line: 143~145
          grid.remove(location);
          grid = null;
          location = null;
          ```

          ​

14.  An actor has a method called setDirection(), if we want to turn 90 degrees, we just use setDirection(getDirection() + Location.RIGHT) or setDirection(getDirection() + 90), to make it turn 90 degrees.

   * The setDirection method in Actor class

     ```java
     //@file: info/gridworld/actor/Actor.java
     //@line: 80~85
     public void setDirection(int newDirection)
         {
             direction = newDirection % Location.FULL_CIRCLE;
             if (direction < 0)
                 direction += Location.FULL_CIRCLE;
         }
     ```

     ​

15. The statement is :  if (!gr.isValid(next)) return false; and this statement ensures that the next location is valid.

   * The statement in the canMove() method

    ```java
     //@file: info/gridworld/actor/Bug.java
     //@line: 96~99
     Location loc = getLocation();
     Location next = loc.getAdjacentLocation(getDirection());
     if (!gr.isValid(next))
          return false;
    ```

16.  The statement is : Actor neighbor = gr.get(next); return (neighbor == null) || (neighbor instanceof Flower);

   This statement lets the Bug move to the next location only if the next location is empty or is flower, so the Bug will never walk into a rock.

   * The statement in the canMove() method, ensuring that next move location is empty or a flower

     ```java
     //@file: info/gridworld/actor/Bug.java
     //@line: 100~101
     Actor neighbor = gr.get(next);
      return (neighbor == null) || (neighbor instanceof Flower);
     ```

17.  The isValid() and get() methods in Grid class are invoked by the canMove() method, because in order to make sure the next location is valid , we need to get the grid and see whether the location is out or not.And we use get() method to get the element in the location of the grid, and to see whether is empty or flower to ensure move correctly.

   * The get() and isValid() methods invoked statements in canMove() method

     ```java
     //@file: info/gridworld/actor/Bug.java
     //@line: 98~101
     if (!gr.isValid(next))
         return false;
     Actor neighbor = gr.get(next);
      return (neighbor == null) || (neighbor instanceof Flower);
     ```

18.   The getAdjacentLocation() method, and the reason using this method is to get the Bug's next location in the direction, and have the next location of the Bug as the Location next.

   * The getAdjacentLocation() method statement using in Bug class

     ```java
     //@file: info/gridworld/actor/Bug.java
     //@line: 98
     Location next = loc.getAdjacentLocation(getDirection());
     ```

19.   The getGrid(), getLocation(), and getDirection() methods, and the reason is that we must use getGrid() function to get the current grid that our Bug is in, and getLocation() provides the right now location of the Bug, and getDirection() provides the direction that our Bug is facing, which is useful to make decision for the next location to move.

   * The getGrid(), getLocation(), and getDirection() methods statements in Bug class

     ```java
     //@file: info/gridworld/actor/Bug.java
     //@line: 93~97
     Grid<Actor> gr = getGrid();
     if (gr == null)
         return false;
     Location loc = getLocation();
     Location next = loc.getAdjacentLocation(getDirection());
     ```

20.   If the location in front of the Bug is invalid, then in the move() method, the Bug will remove itself from the grid.

   * The remove behaviors in the move method

     ```java
     //@file: info/gridworld/actor/Bug.java
     //@line: 78~81
      if (gr.isValid(next))
          moveTo(next);
      else
          removeSelfFromGrid();
     ```

21.   Yes, the variable loc is needed in the move() method, because it stores the location value of the Bug before it moves, and use this old location we can simply put a flower just in it, and in this time the Bug has moved to the new location.It could not be avoided by calling getLocation() several times, because this behavior does not store the old location, and we could not put the behind flower in correct location in this way.

   * The usgae of loc in method move()

     ```java
     //@file: info/gridworld/actor/Bug.java
     //@line: 82~83
     Flower flower = new Flower(getColor());
     flower.putSelfInGrid(gr, loc);
     ```

22.   On the one hand, we can just look at the flower when it's first dropped, and we can know that its color is the same as the Bug.On the other hand, from the codes, we can know that the flower is constructed with the Bug's color, so the flower has the same color with Bug, although the color of flower will get darkened with time going.

   * The statement to new a flower in move() method

     ```java
     //@file: info/gridworld/actor/Bug.java
     //@line: 82
     Flower flower = new Flower(getColor());
     ```

23.   As we all know, Bug has two ways to remove itself, one is just calling removeSelfFromGrid() method, and in this behavior the Bug cannot put a flower in its previous location because this method is inherited from actor, and an actor does not drop flower.However, the second way is calling the move() method and next immediate location is invalid, then it will place a flower into its previous location.

   * The statements in move() method that placing flower though the Bug is removed

     ```java
     //@file: info/gridworld/actor/Bug.java
     //@line: 78~83
     if (gr.isValid(next))
          moveTo(next);
     else
          removeSelfFromGrid();
     Flower flower = new Flower(getColor());
     flower.putSelfInGrid(gr, loc);
     ```

24.   The statements are : Flower flower = new Flower(getColor());  flower.putSelfInGrid(gr, loc);

   * Statement in the Bug class

     ```java
     //@file: info/gridworld/actor/Bug.java
     //@line: 82~83
     Flower flower = new Flower(getColor());
     flower.putSelfInGrid(gr, loc);
     ```

25.   Four times, because each turn() method turrn 45 degrees and 45*4 = 180, totally four times.

   * Turn method() in Bug class

     ```
     //@file: info/gridworld/actor/Bug.java
     //@line: 62~65
     public void turn()
     {
       setDirection(getDirection() + Location.HALF_RIGHT);
     }
     ```

     ​