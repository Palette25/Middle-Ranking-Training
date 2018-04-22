## Part5简答题

1. The isValid() method is specified in Grid class, and is implemented in BoundedGrid and UnboundedGrid class.

   * The specify and implement statements

     ```java
     //@file: info/gridworld/grid/Grid.java
     //@line: 50
     boolean isValid(Location loc);

     //@file: info/gridworld/grid/BoundedGrid.java
     //@line: 60
     public boolean isValid(Location loc)

     //@file: info/gridworld/grid/UnboundedGrid.java
     //@line: 53
     public boolean isValid(Location loc)
     ```

2.  The getValidAdjacentLocations() method in AbstractGrid class calls the isValid() method, and the reason why other methods don't need to call it is that other methods don't need to judge whether the neighboring locations are valid or not, and work to judge is for this getValidAdjacentLocations() method, and other methods just call it to get the valid locations.

   * getValidAdjacentLocations() method calls isValid() method

     ```java
     //@file: info/gridworld/grid/AbstractGrid.java
     //@line: 44
     if (isValid(neighborLoc))
     ```

3.  The get() and getOccupiedAdjacentLocations() methods are called in getNeighbors() method, and we can know that getOccupiedAdjacentLocations() method is implemented in AbstractGrid class, and get() method is implemented in BoundedGrid and UnboundedGrid.

   * The implementations of these two methods

     ```java
     //@file: info/gridworld/grid/AbstractGrid.java
     //@line: 62
     public ArrayList<Location> getOccupiedAdjacentLocations(Location loc)

     //@file: info/gridworld/grid/BoundedGrid.java
     //@line: 85
     public E get(Location loc)

     //@file: info/gridworld/grid/UnboundedGrid.java
     //@line: 66
     public E get(Location loc)
     ```

4.  Because the get() method return the reference of the object storing in the location of the grid or null if there is not any object in that location, so we must use E type to define to type using in get() method.And getEmptyAdjacentLocations() calls get() just to get the object of this location, and if it's null, then the location is empty.Obviously, get() is the only way to check whether the location is empty or not.

   * The usage of get() method

     ```java
     //@file: info/gridworld/grid/AbstractGrid.java
     //@line: 56~57
     if (get(neighborLoc) == null)
         locs.add(neighborLoc);
     ```

5.  The number of totally available directions that can be accessed will be just four: North, South, West and East according to the actor's location, because Location.RIGHT is 90, and Location.HALF_RIGHT is 45.

   * The define statement of RIGHT and HALF_RIGHT

     ```java
     //@file: info/gridworld/grid/Location.java
     //@line: 40, 44
     public static final int RIGHT = 90;
     public static final int HALF_LEFT = -45;
     ```

6.  In the constructer of BoundedGrid, it will throw an IllegalArgumentException when row <= 0 or col <= 0.

   * The statement inside the BoundedGrid's constructer

     ```java
     //@file: info/gridworld/grid/BoundedGrid.java
     //@line: 41~44
     if (rows <= 0)
         throw new IllegalArgumentException("rows <= 0");
     if (cols <= 0)
          throw new IllegalArgumentException("cols <= 0");
     ```

7.  The getNumCols() method return occupantArray[0].length, and this result presents the total length of the two-dim array's first element occupantArray[0], and we know that every rows have same cols, so cols of the grid is just occupantArray[0].length, and constructer promises that rows > 0 and cols > 0.

   * The getNumCols() method's return statement in BoundedGrid class

     ```java
     //@file: info/gridworld/grid/BoundedGrid.java
     //@line: 57
     return occupantArray[0].length;
     ```

8.  The valid location inside the BoundedGrid must have loc.getRow() >= 0 and loc.getRow() < rows and loc.getCol() >= 0 and loc.getCol() < cols to ensure the location is valid.

   * The isValid() method in BoundedGrid class

     ```java
     //@file: info/gridworld/grid/BoundedGrid.java
     //@line: 60~64
     public boolean isValid(Location loc)
     {
         return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
     }
     ```

9.  The type that getOccupiedLocations() method return ArrayList<Location> type, and the time complexity of this method is O(r*c), with location pushing into ArrayList with O(1).

   * The getOccupiedLocations() methos's implementation

     ```java
     //@file: info/gridworld/grid/BoundedGrid.java
     //@line: 66~73
     public ArrayList<Location> getOccupiedLocations()
     {
             ArrayList<Location> theLocations = new ArrayList<Location>();

             // Look at all grid locations.
             for (int r = 0; r < getNumRows(); r++)
             {
                 for (int c = 0; c < getNumCols(); c++)
     ```

10.  The return type of get() is E, which is the type of the occupantArray in BoundedGrid.And needed parameter is the Location of the object that we need to get.The time complexity is O(1).

    * The get() method's implementation

      ```java
      //@file: info/gridworld/grid/BoundedGrid.java
      //@line: 90
      return (E) occupantArray[loc.getRow()][loc.getCol()]
      ```

11. The location to put object is invalid or the object is null, these two conditions will cause execptions throwing.And the time complexity is O(1).

    * The put() method's implementation

      ```java
      //@file: info/gridworld/grid/BoundedGrid.java
      //@line: 93~103
      public E put(Location loc, E obj)
          {
              if (!isValid(loc))
                  throw new IllegalArgumentException("Location " + loc
                          + " is not valid");
              if (obj == null)
                  throw new NullPointerException("obj == null");

              // Add the object to the grid.
              E oldOccupant = get(loc);
              occupantArray[loc.getRow()][loc.getCol()] = obj;
      ```

12.  The return type of the remove() method is E, the type of the occupantArray, and if we want to remove an object from an empty location, then it will not throw an error but just store null into the location, and return null as the result.The time complexity is also O(1).

    * The remove() method's implementation

      ```java
      //@file: info/gridworld/grid/BoundedGrid.java
      //@line: 114~116
      E r = get(loc);
      occupantArray[loc.getRow()][loc.getCol()] = null;
      return r;
      ```

13.  I think the most effcient implementations are the get(), put() and remove() methods, because they all have O(1) complexity, because the storing structure is array and we just use index to access every element.The most ineffcient method is getOccupiedLocations(), because it's time complexity is O(r*c).

    * The ineffcient implementations of getOccupiedLocations

      ```java
      //@file: info/gridworld/grid/BoundedGrid.java
      //@line: 66~73
      public ArrayList<Location> getOccupiedLocations()
      {
              ArrayList<Location> theLocations = new ArrayList<Location>();

              // Look at all grid locations.
              for (int r = 0; r < getNumRows(); r++)
              {
                  for (int c = 0; c < getNumCols(); c++)
      ```

      ​

14. * For the HashMap can be used, the Location class must implement the hashCode() and equals() method to test whether two locations are the same.And hashCodes must be the same when equals() return true for two locations.


    * For the TreeMap can be used, the key of map must be Comparable, so the Location class must be Comparable, implementing the abstract class Comparable and finish the compare() method.

    * Location class satisfy all these two requirements.

    * The define statement of Location class

      ```java
      //@file: info/gridworld/grid/Location.java
      //@line: 28, 218~221
      public class Location implements Comparable
      public int hashCode()
      {
         return getRow() * 3737 + getCol();
      }
      ```

15. * With the map storing structure, the null location value can be used as a key.And in UnboundedGrid, the isValid() method always returns true casue every not-null value in UnboundedGrid is valid, but we don't have a method to check about the null, so in get, put and remove methods we use the checks for null location, avoid errors happening.

    * In the BoundedGrid, we have isValid() method to check for every location is valid or not, and if the location is null, then it will cause the NullPointerException.

    * The isValid() method in UnboundedGrid class

      ```java
      //@file: info/gridworld/grid/UnboundedGrid.java
      //@line: 53~56
      public boolean isValid(Location loc)
      {
         return true;
      }
      ```

16.  The average time complexity for these thress methods: get, put and remove is O(1) while using HashMap.And if we use TreeMap, the time complexity is O(logn).

    * The main behaviors in these three methods

      ```java
      //@file: info/gridworld/grid/UnboundedGrid.java
      //@line: 70, 79, 86
      return occupantMap.get(loc);
      return occupantMap.put(loc, obj);
      return occupantMap.remove(loc);
      ```

17.  In general, the getOccupiedLocations() method will return the ArrayList of location in a different order.Because that the HashMap store the key-value structure in a Hash Table, and with this table we can access the value with the same order of the key.But the TreeMap uses a binary balanced tree to store data,, and tranverse them with inorder method, which will cause the different order of result.

    * Implementatioin of getOccupiedLocations()

      ```java
      //@file: info/gridworld/grid/UnboundedGrid.java
      //@line: 61~62
      for (Location loc : occupantMap.keySet())
           a.add(loc);
      ```

18.  Yes, a map can be used to implement the BoundedGrid, and if the HashMap is used to implement, then the time complexity of put, get and remove will keep in O(1), but the complexity of getOccupiedLocations() willbe reduced to O(n), n is the total number of the storing object. However, when the BoudnedGrid's size is very big and it stores a large number of object, then in this time the HashMap will use more memory because it both store the location and the object. However, the array only store the object in a index.

    * The array implementation of BoundedGrid class

      ```java
      //@file: info/gridworld/grid/BoundedGrid.java
      //@line: 31
      private Object[][] occupantArray;
      ```

      ​

    ​