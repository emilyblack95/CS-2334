import java.util.ArrayList;
/**
 * Project 2
 * CS 2334 - Section 010
 * 2/26/15
 *
 */
public class City implements Comparable<City>
{
	private ArrayList<Person> residents;
	private State myState;
	private String cityName;
	
	/**
	 * Default Constructor
	 */
	public City()
	{ //defaults to Norman, OK
		this.myState = new State("OK");
		this.cityName = "Norman";
		this.residents = new ArrayList<Person>();
	}
	
	/**
	 * Constructor with a city and State
	 * @param newCity
	 * @param s
	 */
	public City(String newCity, State s)
	{ 
		this.myState = s;
		this.cityName = newCity;
		this.residents = new ArrayList<Person>();
	}
	
	/**
	 * Converts cityName to a String
	 */
	public String toString()
	{ 
		return this.cityName;
	}
	
	/**
	 * Accessor
	 * @return residents
	 */
	public ArrayList<Person> getResidentList()
	{
		return this.residents;
	}
	
	/**
	 * Accessor
	 * @return myState
	 */
	public State getState()
	{ 
		return this.myState;
	}
	
	/**
	 * Fills an ArrayList of Person objects with all residents of the city from the specified file
	 * @param listOfData
	 * @return
	 */
	public ArrayList<Person> fillResidentList(ArrayList<Person> listOfData) 
	{
		for (int i=0; i < listOfData.size(); i++)
		{
			Person temp = listOfData.get(i);
			if (temp.getCityLOC().equalsIgnoreCase(this.cityName) && temp.getStateLOC().equalsIgnoreCase(this.myState.toString())) //if the cities & states are the same
				this.residents.add(listOfData.get(i)); //adds person to the list
		}
		return this.residents;
	}
	
	/**
	 * Compares to see if 2 cities are equal and their states are equal
	 * @param other
	 * @return
	 */
	public boolean equals(City other)
	{ 
		boolean isEqual = this.cityName.equalsIgnoreCase(other.toString()) && this.myState.toString().equalsIgnoreCase(other.getState().toString());
		return isEqual;
	}
	
	/**
	 * Assumes the cities are in the same state when this method is called. Compares alphabetically
	 * Compares 2 cities of the SAME STATE!
	 */
	public int compareTo(City other) 
	{
		return this.cityName.compareTo(other.toString()); //calls the string compareTo method for the city names 
	}
}