package mst;

public class IndO
{
	int id;
	int cost;
	int parent;
	
	public IndO(int id)
	{
		this.id = id;
		cost = Integer.MAX_VALUE;
		parent = -1;
	}
	
	public void updCost(int cost)
	{
		if(this.cost > cost)
			this.cost = cost;
	}
	
	public String toString()
	{
		return "(" + id + "," + cost + "," + parent + ")";
	}
}
