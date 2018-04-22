## Part3 Test Report

1. ### The codes of five test functions inside the JumperTest.java

   * ```java
     //Test1 Case, testing flower in front of two cells
         @Test
         public void test1(){
             ActorWorld world = new ActorWorld();
             Jumper jump = new Jumper();
             Flower flo = new Flower();
             world.add(new Location(3, 3), jump);
             world.add(new Location(1, 3), flo);
             jump.act();
             //assert to see if equal
             assertEquals(jump.getLocation(), new Location(1, 3));
         }
     ```

   * The test case1 is to test that the flower two cells in front of the jumper, and we use the assertEquals method to test whether the jumper is jumping to the flower's location, and after testing, we can see it's true.

     ​

   * ```java
     //Test2 Case, testing two cells in front of it is out of grid
         @Test
         public void test2(){
             ActorWorld world = new ActorWorld();
             Jumper jump = new Jumper();
             int oldDirection = jump.getDirection();
             world.add(new Location(1, 8), jump);
             jump.act();
             assertEquals(jump.getLocation(), new Location(1, 8));
             assertEquals(jump.getDirection(), oldDirection + Location.HALF_RIGHT);
         }
     ```

   * The test case2 is to test that the jumper's two cells front location is out of grid, and in my design, I let the jumper turn when the location two cells front is invalid, so in this case the jumper will turn and stay in the old location.So the assertEquals result will be true.

     ​

   * ```java
     //Test3 Case, testing jumper facing an edge of grid
         @Test
         public void test3(){
             ActorWorld world = new ActorWorld();
             Jumper jump = new Jumper();
             world.add(new Location(0, 9), jump);
             jump.act();
             assertEquals(jump.getLocation(), new Location(0, 9));
         }
     ```

   * The test case3 is to test that the jumper is facing the edges of the grid, and in this case, the jumper will turn and find a new direction to jump, the result of assertEquals method will be true.

     ​

   * ```java
     //Test4 case, another actor two cells in front of it
         @Test
         public void test4(){
             ActorWorld world = new ActorWorld();
             Jumper jump = new Jumper();
             Bug bug = new Bug();
             world.add(new Location(3, 4), jump);
             world.add(new Location(1, 4), bug);
             jump.act();
             assertEquals(jump.getLocation(), new Location(3, 4));
         }
     ```

   * The test case4 is to test that there is another actor two cells in front of the jumper, and in this case the jumper will also turn, and stay in old location, the result will also be true.

     ​

   * ```java
     //Test5 case, two jumpers encounting the path
         @Test
         public void test5(){
             ActorWorld world = new ActorWorld();
             Jumper jump1 = new Jumper();
             Jumper jump2 = new Jumper();
             world.add(new Location(3, 3), jump1);
             world.add(new Location(1, 1), jump2);
             jump2.turn();
             jump2.turn();
             world.step();
             assertEquals(jump1.getLocation(), new Location(3, 3));
             assertEquals(jump2.getLocation(), new Location(1, 3));
         }
     ```

   *  The test case5 is to test the situation of two jumpers encounter each other in their moving way, and in this case, the faster one(always the jumper that close to left-up part of the grid, because the grid stores them in array and access them in a loop) will get into the location first, and the slower one will stay in old location. The assertEquals result will be true.

     ​

2.  ### The result of running JumperTest.java in JUnit

   * ```makefile
     test:
         [junit] Running JumperTest
         [junit] Tests run: 5, Failures: 0, Errors: 0, Time elapsed: 0.132 sec

     BUILD SUCCESSFUL
     Total time: 2 seconds
     ```