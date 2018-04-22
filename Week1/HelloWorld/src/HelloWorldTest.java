package helloworld;

import org.junit.*;
import static org.junit.Assert.*;

public class HelloWorldTest{
	@Test
	public void test(){
		HelloWorld temp = new HelloWorld();
		System.out.println(temp.say());
	}
}