/**
* JumperTest.java
*/

import org.junit.*;
import static org.junit.Assert.*;
import info.gridworld.actor.*;
import info.gridworld.grid.Location;

public class JumperTest{

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

    //Test3 Case, testing jumper facing an edge of grid
    @Test
    public void test3(){
        ActorWorld world = new ActorWorld();
        Jumper jump = new Jumper();
        world.add(new Location(0, 9), jump);
        jump.act();
        assertEquals(jump.getLocation(), new Location(0, 9));
    }

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
}