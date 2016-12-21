
/**
 * MapCoord stores coordinates for members of Map.java
 * 
 * @author Christopher Kramer (*Do not change*)
 * @version 1.0
 * @since 3/14/15
 *
 */
public class MapCoord {
	
	private double lat;
	private double lon;
	private String name;
	final private double mapx=-125;
	final private double mapy=50;
	final private double mapsx=-66;
	final private double mapsy=24;
	
	/**
	 * @param name name of team
	 * @param lat latitude  (-90, 90)
	 * @param lon longitude (-180,180)
	 */
	public MapCoord(String name,double lat, double lon)
	{
		this.name=name;
		this.lat=lat;
		this.lon=lon;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public String getName()
	{
		return name;
	}
	public void setLat(int a)
	{
		lat=a;
	}
	public void setLon(int b)
	{
		lon=b;
	}
	public double getLat()
	{
		return lat;
	}
	public double getLon()
	{
		return lon;
	}
	/*
	 * Returns: Returns the scaled location of the map pixel between 0.0 and 1.0. 
	 * Multiply by window size for correct pixel location
	 */
	public double[] getLoc()
	{
		double[] out=new double[2];
		out[0]=(lat-mapy)/(mapsy-mapy);
		out[1]=(lon-mapx)/(mapsx-mapx);
		return out;
	}
}