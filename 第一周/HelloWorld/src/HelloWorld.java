package helloworld;

import org.junit.*;
import HelloWorld.*;
import static org.junit.Assert.*;

public class HelloWorld{

	public static void main(String[] args){
		System.out.println("Hello, World");
	}

	public String say(){
		String result = "Hello, World";
		return result;
	}
}