import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * Piechart will chart the ages in data. give State name and data to be mapped.
 * 	Still a little buggy for large data sets but that will be corrected if necessary
 * Please verify output: User assumes all liability for results
 * 
 * @author Christopher Kramer (*Do not change*)
 * @version 1.0
 * @since 3/14/15
 *
 */
public class PieChart extends JFrame {
	
	private static final long serialVersionUID = -7602137048032105541L;
	PiePanel pie;
			
	/**
	 * @param stateAbbrev Abbreviation of state data. Used for title only
	 * @param data	List of ages
	 */
	public PieChart(String stateAbbrev, ArrayList<Integer> data)
	{
		validate(data);
		setTitle("PeoplAce (age): Ages of Team Members from "+stateAbbrev);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(400,400);
		//this.setLocationRelativeTo(null);
		pie= new PiePanel(data);
		this.add(pie);
		pie.update();
		this.setResizable(false);
		this.setVisible(true);
	}
	public void update()
	{
		pie.update();
	}
	private void validate(ArrayList<Integer> data)
	{
		if(data==null)
			throw new RuntimeException("Pie chart data cannot be null");
		for(int a: data)
		{
			if(a<0 || a>199)
				throw new RuntimeException("Ages must fit in the range [0,199]");
		}
	}
	private class PiePanel extends JPanel
	{
		private static final long serialVersionUID = -4500891316873194656L;
		ArrayList<Integer> data;
		ArrayList<Integer> dictionary;
		int[] count;
		int size;
		
		private PiePanel(ArrayList<Integer> d)
		{
			data=d;
			dictionary=new ArrayList<Integer>();
			count=null;
		}
		private void update()
		{
			dictionary.clear();
			count=new int[200];
			for(int a: data)
			{
				if(!dictionary.contains(a))
				{
					dictionary.add(a);
				}
				count[a]++;
			}
			Collections.sort(dictionary);
			repaint();
			
		}
		private ArrayList<Integer> segSize()
		{
			double remainder=0;
			if(count==null)
				return null;
			ArrayList<Integer> out = new ArrayList<Integer>();
			int total=0;
			for(int a: count)
			{
				total+=a;
			}
			for(int a: dictionary)
			{
				double rem=(360.0*(count[a]+remainder))/total-(int)((360.0*(count[a]+remainder))/total);
				out.add((int)((360.0*(count[a]+remainder))/total));
				remainder=rem;
			}
			return out;
		}
		private Color getColor(int a)
		{
			
			return Color.getHSBColor((float)(1.0*a/size), .5f, 1f);
		}
		public void paint(Graphics g)
		{
			ArrayList<Integer> segs=segSize();
			if(segs==null)
				return;
			int height=getHeight();
			int width=getWidth();
			int index=0;
			int currAngle=0;
			size=segs.size();
			for(Integer a: segs)
			{
				index++;
				g.setColor(getColor(index));
				g.fillArc(0, 0, width, height, currAngle, a);
				
				currAngle+=a;
			}
			index=0;
			currAngle=0;
			g.setColor(Color.black);
			for(int i=0;i<segs.size();i++)
			{
				double x=width/2;
				double y=height/2;
				currAngle+=segs.get(i)/2;
				x-=Math.cos(Math.toRadians(currAngle+180))*x/1.6+3;
				y+=Math.sin(Math.toRadians(currAngle+180))*y/1.6+5;
				g.drawString(dictionary.get(i)+"", (int)x, (int)y);
				index++;
				currAngle+=(segs.get(i)/2+segs.get(i)%2);
			}
			g.drawOval(0, 0, width, height);
		}
	}
}