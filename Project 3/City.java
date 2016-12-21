import java.io.Serializable;
import java.util.ArrayList;
/**
 * Project 3
 * CS 2334 - Section 010
 * 2/26/15
 *
 * This class models a city. Each city has a name, state where it is located, and latitude/longitude coordinates.
 */
public class City implements Comparable<City>, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6351378842249704591L;
	private ArrayList<Person> residents;
	private State myState;
	private String cityName;
	private int latitude;
	private int longitude;
	
	/**
	 * Default Constructor - defaults to null values and lat/lon of 0 if no parameters are specified.
	 */
	public City()
	{ 
		residents = new ArrayList<Person>();
		myState = null;
		cityName = null;
		latitude =  0;
		longitude = 0;

	}
	
	/**
	 * Constructor with a city and State
	 * @param newCity	Name of the city as a String
	 * @param s			State object where the city is located
	 */
	public City(String newCity, State s)
	{ 
		residents = new ArrayList<Person>();
		cityName = newCity;
		myState = s;

	}
	
	/**
	 * Constructor
	 * @param cityName	name of the city as a string
	 * @param s			State object where the city is located
	 * @param lat		double representation of the latitude
	 * @param lon		double representation of the longitude
	 */
	public City(String newCityName, State s, double lat, double lon)
	{
		residents = new ArrayList<Person>();
		cityName = newCityName;
		myState = s;
		latitude = (int) (Math.round(lat));
		longitude = -1 * ((int) (Math.round(lon)));
		
	}
	
	/**
	 * Returns latitude as an integer.
	 * @return integer value of latitude
	 */
	public int getLatitude(){
		return this.latitude; 	
	}
	
	/**
	 * Returns longitude as an integer.
	 * @return  integer value of longitude
	 */
	public int getLongitude(){
		return this.longitude; 	
	}
	
	/**
	 * Converts city to a String by returning its name.
	 * @return 	name of the city
	 */
	public String toString()
	{ 
		return this.cityName;
	}
	
	/**
	 * Returns the list of people (as Person objects) living in that city
	 * @return list of residents
	 */
	public ArrayList<Person> getResidentList()
	{
		return this.residents;
	}
	
	/**
	 * Returns the state object where the city is located.
	 * @return state abbreviation
	 */
	public State getState()
	{ 
		return this.myState;
	}
	
	/**
	 * Sets the latitude to the given integer.
	 * @param lat	value to set latitude to
	 */
	public void setLatitude(int lat)
	{
		latitude = lat;
	}
	
	/**
	 * Sets the longitude to the given integer.
	 * @param lon	value to set longitude to
	 */
	public void setLongitude(int lon)
	{
		longitude = lon;
	}
	
	/**
	 * Fills an ArrayList of Person objects with all residents of the city from the specified file.
	 * @param listOfData	ArrayList of Person objects where residents are listed
	 * @return				list of residents living in the city
	 */
	public ArrayList<Person> fillResidentList(ArrayList<Person> listOfData) 
	{
		listOfData.trimToSize();
		Person temp = null;
		for (int i=0; i < listOfData.size(); i++)
		{
				temp = listOfData.get(i);
				if (temp.getCity().toString().equalsIgnoreCase(this.cityName) && 
						temp.getState().toString().equalsIgnoreCase(this.myState.toString())) //if the cities & states are the same
				{
					this.residents.add(temp); //adds person to the list 
				}
			
		}
		return this.residents;	
		
	}
	
	/**
	 * Compares to see if two cities are equal and their states are equal,
	 * @param 	other		the city this city is being compared to
	 * @return				true if the cities have the same name, false otherwise
	 */
	public boolean equals(City other)
	{ 
		boolean isEqual = this.cityName.equalsIgnoreCase(other.toString()) && this.myState.toString().equalsIgnoreCase(other.getState().toString());
		return isEqual;
	}
	
	/**
	 * Assumes the cities are in the same state when this method is called. Compares alphabetically.
	 * @param 	other		the city this city is being compared to
	 * @return 				0 if the cities have the same name, a negative integer if this city comes 
	 * 						first alphabetically, or a positive integer if this city comes after the other city	
	 */
	public int compareTo(City other) 
	{
		return this.cityName.compareTo(other.toString()); //calls the string compareTo method for the city names 
	}
}
