package mst;

import java.util.HashMap;

public class Node
{
	public static int R = 300;
	int id;
	HashMap<Integer, Integer> connections;
	
	public Node(int id)
	{
		this.id = id;
		connections = new HashMap<Integer, Integer>();
	}
	
	public int getY(int y0, double t)
	{
		return (int) Math.round(y0 + R*Math.sin(t));
	}
	
	public int getX(int x0, double t)
	{
		return (int) Math.round(x0 + R*Math.cos(t));
	}
	
	public void addConnection(int id, int cost)
	{
		if(!(connections.containsKey(id) && connections.get(id) < cost))
			connections.put(id, cost);	
	}
	
	public String toString()
	{
		String cons = "";
		for(Integer key : connections.keySet())
			cons += key + ":" + connections.get(key) + " ";
		return id + "-> " + cons;
	}
}
