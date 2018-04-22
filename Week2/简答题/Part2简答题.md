The instance variable sideLength is the border length of the moving square trail,when steps number is equal to sideLength, it turns twice to turn to a new direction that adding ninty degrees to its old direction.
* if steps is less than sideLength and can move:
```
	//@file: info/gridworld/actor/BoxBug.java
	//@line: 45~49
	if (steps < sideLength && canMove())
 {
     move();
     steps++;
  }
	```
	
* else if steps is equal to sideLength or cannot move:
```
	//@file: info/gridworld/actor/BoxBug.java
	//@line: 50~55
	else
 {
   turn();
   turn();
   steps = 0;
  }
	```

	The varible steps counts the step number that BoxBug has been moved in the direction, helping BoxBug to move exactly the same steps that is equal to sideLength,   and when BoxBug turns to change its direction, steps changes to zero.
* When steps is less than sideLength and can move, BoxBug keeps moving, steps plus one:
```
//@file: info/gridworld/actor/BoxBug.java
//@line: 45~49
if (steps < sideLength && canMove())
        {
            move();
            steps++;
        }
```
* When steps is equal to sideLength or cannot move, steps changes to zero:
```
//@file: info/gridworld/actor/BoxBug.java
//@line: 50~55
else
        {
            turn();
            turn();
            steps = 0;
        }
```


Because if we want to make the BoxBug to move in the trail of square, we need to turn twice to make its direction plus ninty degress, when steps becomes equal to sideLength.
* Turn method in Bug class:
```
//@file: info/gridworld/actor/Bug.java
//@line: 62~65
public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }
```

* HALF_RIGHT in Location class:
```
//@file: info/gridworld/grid/Location.java
//@line: 48
public static final int HALF_RIGHT = 45;
```


Because the BoxBug class is extended from the Bug class, and in the Bug class we have move method.
* BoxBug extends from Bug class
```
//@file: info/gridworld/actor/BoxBug.java
//@line: 25
public class BoxBug extends Bug
```
* Bug class has move method:
```
//@file: info/gridworld/actor/Bug.java
//@line: 71
public void move()
```


Yes, the square pattern will always be the same because once the BoxBug is constructed, then the sideLength will not be changed.
* The variable sideLength decide the pattern, and it will not be changed:
```
//@file: info/gridworld/actor/BoxBug.java
//@line: 34~38
public BoxBug(int length)
    {
        steps = 0;
        sideLength = length;
    }
```


No, the moving path of a BoxBug will never change, because the BoxBug is always moves in the trail of square, and square is a shape of ring closure, and the sideLength will never change in the act method, so it always travels in the same path ,with steps changing and reseting.
* The act method of BoxBug:
```
//@file: info/gridworld/actor/BoxBug.java
//@line: 45~55
if (steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        else
        {
            turn();
            turn();
            steps = 0;
        }
```

When the steps in equal to the sideLength, then the value of steps will be set to zero.
* Setting steps to 0 when equal:
```
//@file: info/gridworld/actor/BoxBug.java
//@line: 50~55
else
        {
            turn();
            turn();
            steps = 0;
        }
```
