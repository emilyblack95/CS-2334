import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * Map for PeoplAce. Use consists of three parts: 
 * 	places consists of all locations present on map.
 * 	teams consists of a hashmap mapping team names to their corresponding map coordinate
 * 	memberLocs consists of a hashmap mapping team names to a list of their members home map coordinates
 * Please verify output: User assumes all liability for results
 * 
 * @author Christopher Kramer (*Do not change*)
 * @version 1.0
 * @since 3/14/15
 *
 */
public class Map extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private MapPanel map;
	private ArrayList<MapCoord> places; 
	private HashMap<String,MapCoord> teams;
	private HashMap<String,ArrayList<MapCoord>> teamLocs;
	
	/**
	 * @param places list of all MapCoord
	 * @param teams	 list of all team MapCoord mapped to team name
	 * @param memberLocs holds lists of all team members' MapCoords mapped to team name
	 */
	public Map(ArrayList<MapCoord> places, HashMap<String,MapCoord> teams,HashMap<String,ArrayList<MapCoord>> memberLocs)
	{
		setTitle("PeoplAce (location): Team Location and Birthplaces of Team Members");
		this.places=places;
		this.teams=teams;
		this.teamLocs=memberLocs;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(805,464);
		this.setLocationRelativeTo(null);
		map= new MapPanel();
		this.add(map);
		map.update();
		//this.setResizable(false);
		this.setVisible(true);
	}
	private class MapPanel extends JPanel
	{

		private static final long serialVersionUID = 1L;
		Image image;
		public MapPanel()
		{
			image=(new ImageIcon("map.jpg")).getImage();
		}
		public void update()
		{
			repaint();
		}
		public void paint(Graphics g)
		{
			g.drawImage(image, 0, 0,getWidth(),getHeight(),this);
			for(MapCoord a: places)
			{
				int off = g.getFontMetrics().stringWidth(a.getName())/2;
				double[] coo= a.getLoc();
				g.drawString(a.getName(), (int)(getWidth()*coo[1]-off), (int)(getHeight()*coo[0])-5);
				g.fillOval((int)(getWidth()*coo[1]), (int)(getHeight()*coo[0]), 4, 4);
			}
			g.setColor(Color.red);
			for(String a: teams.keySet())
			{
				double[] home = teams.get(a).getLoc();
				for(MapCoord c: teamLocs.get(a))
				{
					double[] loc=c.getLoc();
					g.drawLine((int)(getWidth()*home[1]+2), (int)(getHeight()*home[0]+2),(int)(getWidth()*loc[1]+2), (int)(getHeight()*loc[0])+2);
					
				}
			}
		}
	}
}