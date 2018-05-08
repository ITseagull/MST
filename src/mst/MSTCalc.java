package mst;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class MSTCalc
{
	List<Node> nodes;
	Queue<IndO> mst;
	public MSTCalc(String path) throws FileNotFoundException
	{
		nodes = new LinkedList<Node>();
		mst = new LinkedList<IndO>();
		
		read(path);
		System.out.println(Arrays.toString(nodes.toArray()));
		Node.R = nodes.size()*(Math.min(400/nodes.size(), 50));
		calc();
		System.out.println(Arrays.toString(mst.toArray()));
	}
	
	private void read(String path) throws FileNotFoundException
	{
		Scanner in = new Scanner(new File(path));
		while(in.hasNextLine())
		{
			String[] line = in.nextLine().split(" ");
			Node node = new Node(Integer.parseInt(line[0]));
			for(int i = 1; i < line.length; i++)
				node.addConnection(Integer.parseInt(line[i].split(":")[0]), Integer.parseInt(line[i].split(":")[1]));
			nodes.add(node);
		}
		in.close();
	}
	
	private void calc()
	{
		PriorityQueue<IndO> pq = new PriorityQueue<IndO>(nodes.size(), (a,b) -> Integer.compare(a.cost, b.cost));
		IndO start = new IndO(0);
		start.cost = 0;
		pq.add(start);
		for(int i = 1; i < nodes.size(); i++)
			pq.add(new IndO(nodes.get(i).id));
		
		while(!pq.isEmpty())
		{
			IndO ind = pq.poll();
			Node node = nodes.get(ind.id);
			mst.add(ind);
			List<IndO> t = new ArrayList<IndO>();
			while(!pq.isEmpty())
			{
				IndO tmp = pq.poll();
				if(node.connections.containsKey(tmp.id))
				{	
					int ncost = ind.cost + node.connections.get(tmp.id);
					if(tmp.cost > ncost)
					{
						tmp.cost = ncost;
						tmp.parent = ind.id;
					}
				}
				t.add(tmp);
			}
			pq.addAll(t);
		}
	}

}
