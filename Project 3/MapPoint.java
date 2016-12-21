/**
 * Project 3
 * CS 2334 - Section 010
 * 2/26/15
 *
 * This class models the coordinates of a point on a map and keeps track of the label for that point.
 * 
 */
public class MapPoint {
	
	private int x;
	private int y;
	private String city;
	
	/**
	 * Constructor - creates a map point based on specified data.
	 * @param	newX		x-coordinate
	 * @param 	newY		y-coordinate
	 * @param   cityName	label for point on map
	 */
	public MapPoint(int newX, int newY, String cityName)
	{
		x = newX;
		y = newY;
		city = cityName;
	}
	
	/**
	 * Returns the x-coordinate of the point
	 * @return	x-coordinate
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Returns the y-coordinate of the point
	 * @return	y-coordinate
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Returns the label of the point.
	 * @return	name of city
	 */
	public String getCity()
	{
		return city;
	}
	

}
