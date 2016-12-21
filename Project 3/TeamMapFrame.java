import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Project 3
 * CS 2334 - Section 010
 * 2/26/15
 *
 * This class creates a plate carree map of cities in the United States.
 * 
 * Image US_Map.png taken from http://commons.wikimedia.org/wiki/File:Usa-equidistant.png (size: 3120x1290 pixels).
 * Code to load an image into the program taken from http://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel.
 */
public class TeamMapFrame extends JPanel {
	
	private static final long serialVersionUID = -6874273684121507045L;
	private ArrayList<MapPoint> points; 
	private Image image; 
	
	/**
	 * Pixel width of each point on the map, set to 2.
	 */
	private static final int WIDTH = 2;
	
	/**
	 * Pixel height of each point on the map, set to 2.
	 */
	private static final int HEIGHT = 2; 

	/**
	 * Constructor - creates a Team Map Frame object from the given list of points to plot.
	 * @param	ps	list of coordinates to plot
	 */
	public TeamMapFrame(ArrayList<MapPoint> ps)
	{
		super();
		points = ps;
	}
	
	
	/**
	 * This method creates the map itself.
	 * Algorithm: x-coordinate = (3120 pixels)/(57 degrees) * (longitude - 67)
	 * 			  y-coordinate = (1290 pixels)/(24 degrees) * (latitude - 25)
	 *
	 * @param	g	Graphics object
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		try {
			image = ImageIO.read(new File("US_Map.png")); //loads map image
			
		} catch (IOException e) {
			System.out.println("Image not found.");
		}

		JLabel picLabel = new JLabel(new ImageIcon(image));
			add(picLabel);	
		g.drawImage(image, 0, 0, null); //draws map //ImageObserver is null... should it have an observer?
		
		for (MapPoint p : points) //for each loop - loops through all of the points in points list
		{
			int x = (3120/57) * (p.getX() - 67);
			int y = (1290/24) * (p.getY() - 25);
			g.fillOval(x, y, WIDTH, HEIGHT);
		}
		paint(g);
	}

}
