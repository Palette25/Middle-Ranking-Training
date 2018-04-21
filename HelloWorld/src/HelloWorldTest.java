import org.junit.*;
import static org.junit.Assert.*;

public class HelloWorldTest{
	HelloWorld temp = new HelloWorld();

	@Test
	public void test1(){
		System.out.println(temp.say());
		assertEquals(temp.say(), "Hello, World");
	}

	@Test
	public void test2(){
		assertEquals(temp.say(), "Hello, World");
	}
}