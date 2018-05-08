package mst;

import java.awt.Color;

public class Connection
{
	Node a;
	Node b;
	Color color;
	
	public Connection(Node a, Node b, Color color)
	{
		this.color = color;
		if(a.id < b.id)
		{
			this.a = a;
			this.b = b;
		}else
		{
			this.a = b;
			this.b = b;
		}
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Connection o = (Connection) obj;
		if(a.id == o.a.id)
			return b.id == o.b.id;
		else if(b.id == o.a.id)
			return a.id == o.b.id;
		return false;
	}
}
