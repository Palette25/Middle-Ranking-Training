## Part5 README

1. Using Part5 methods:
   * I put the build.xml into the zip file, so if you want to run the SparseBoundedGridRunner, just enter ant in the termial window, and you can see the Runner running.
   * In this Part, I create SparseBoundedGrid, SparseBoundedGrid2, and SparseBoundedGrid3, UnboundedGrid2. And the SparseBoundedGrid2 use HashMap 
   to store the data, SparseBoundedGrid3 use TreeMap to store elements.All of
   these classes can be use in the Runner.



2. Answering the questions in Part5

* Q1: Consider using a `HashMap` **or** `TreeMap` to implement the `SparseBoundedGrid`. How could you use the UnboundedGrid class to accomplish this task? Which methods of `UnboundedGrid` could be used without change? Fill in the following chart to compare the expected Big-Oh efficiencies for each implementation of the `SparseBoundedGrid`.

* A: To create a SparseBoundedGrid in map structure, we can just don't need to change some method in UnboundedGrid, such as: get(), put(), and remove(), getOccupiedLocations().And for creating a new SparseBoundedGrid, we can just use some of the codes in UnboundedGrid, and add some other special methods.Next is the Big-Oh efficiencies table.

  ​

|           Methods            | SparseGridNode version | LinkedList<OccupantInCol> version | HashMap version | TreeMap version |
| :--------------------------: | :--------------------: | :-------------------------------: | :-------------: | :-------------: |
|         getNeighbors         |          O(c)          |               O(c)                |      O(1)       |     O(logn)     |
|  getEmptyAdjacentLocations   |          O(c)          |               O(c)                |      O(1)       |     O(logn)     |
| getOccupiedAdjacentLocations |          O(c)          |               O(c)                |      O(1)       |     O(logn)     |
|     getOccupiedLocations     |         O(r+n)         |              O(r+n)               |      O(n)       |      O(n)       |
|             get              |          O(c)          |               O(c)                |      O(1)       |     O(logn)     |
|             put              |          O(c)          |               O(c)                |      O(1)       |     O(logn)     |
|            remove            |          O(c)          |               O(c)                |      O(1)       |     O(logn)     |

3. * Q2: What is the Big-Oh efficiency of the get method? What is the efficiency of the put method when the row and column index values are within the current array bounds? What is the efficiency when the array needs to be resized?

   * A: The Big-Oh of get: O(1), cause we just get the row and column, simply use index the access the element.

     ​     The Big-Oh of put: O(1) when the index is in the current bound range, we just change the element's value,

     ​				   O(largeLevel*16 * largeLevel*16) when we need to enlarge the bound, just get the loop to 				

     ​				  copy values and save values, and here largeLevel is the size level, initialing is 1, and 

     ​				 multiply 2 whenever need to enlarge the size.