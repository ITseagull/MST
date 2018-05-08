package mst;

import java.io.FileNotFoundException;

public class Main
{

	public static void main(String[] args) throws FileNotFoundException
	{
		String path = "ExampleData/MST.txt";
		new MSTFrame(new MSTCalc(path));
	}

}
