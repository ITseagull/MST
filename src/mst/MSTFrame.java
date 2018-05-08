package mst;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MSTFrame extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7661394176404724217L;
	private static final int DIAM = 25;
	private MSTCalc calc;
	List<Connection> lines;
	JFrame frame;
	
	public MSTFrame(MSTCalc calc)
	{
		this.calc = calc;
		frame = this;
		List<Node> nodes = calc.nodes;
		lines = new LinkedList<Connection>();
		
		for (int i = 0; i < nodes.size(); i++)
		{
			Node node = nodes.get(i);
			for (int n : node.connections.keySet())
			{
				Connection con = new Connection(node, nodes.get(n), Color.CYAN);
				if (!lines.contains(con))
				{
					lines.add(con);
				}
			}
		}

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		getContentPane().add(panel);
		setSize(1600, 900);

		JButton button = new JButton("Next step");
		panel.add(button);

		button.addActionListener((a) ->
		{
			if (!calc.mst.isEmpty())
			{
				IndO node = calc.mst.poll();
				for(Connection con : lines)
				{
					if((con.a.id == node.id && con.b.id == node.parent) || (con.a.id == node.parent && con.b.id == node.id))
						con.color = Color.RED;
					else if(con.a.id == node.id || con.b.id == node.id && !con.color.equals(Color.RED))
						con.color = Color.ORANGE;
				}
				this.repaint();
				if(calc.mst.isEmpty())
				{
					button.setText("Done");
					button.setEnabled(false);
				}	
			}
				
		});
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		if (calc != null)
		{

			List<Node> nodes = calc.nodes;

			for (Connection con : lines)
				drawLine(g2, con);

			for (int i = 0; i < nodes.size(); i++)
			{
				g2.setColor(Color.BLACK);
				Node node = nodes.get(i);
				int x = getX(node);
				int y = getY(node);
				g2.fillOval(x, y, DIAM, DIAM);
				char[] num = (node.id + "").toCharArray();
				g2.setColor(Color.WHITE);
				g2.drawChars(num, 0, num.length, x + (DIAM / 2 - num.length * 3), y + (DIAM / 2 - num.length / 2 + 4));
			}

		}
	}

	public int getX(Node node)
	{
		return node.getX(getWidth() / 2, 2 * Math.PI * node.id / calc.nodes.size());
	}

	public int getY(Node node)
	{
		return node.getY(getHeight() / 2, 2 * Math.PI * node.id / calc.nodes.size());
	}

	public void drawLine(Graphics2D g, Connection con)
	{
		Color prev = g.getColor();
		Font font = g.getFont();
		g.setColor(con.color);
		int xa = getX(con.a) + DIAM / 2;
		int ya = getY(con.a) + DIAM / 2;

		int xb = getX(con.b) + DIAM / 2;
		int yb = getY(con.b) + DIAM / 2;

		int xc = (xb + xa) / 2;
		int yc = (yb + ya) / 2;

		Line2D lin = new Line2D.Float(xa, ya, xb, yb);
		g.draw(lin);

		g.setFont(new Font(font.getFontName(), 20, 20));
		g.setColor(Color.BLACK);
		char[] num = (con.a.connections.get(con.b.id) + "").toCharArray();
		if (num[0] != 'n')
			g.drawChars(num, 0, num.length, xc + 15, yc + 15);

		g.setColor(prev);
		g.setFont(font);
	}

}
