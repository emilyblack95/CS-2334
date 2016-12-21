import java.util.ArrayList;
import java.util.Collections;
/**
 * Project 2
 * CS 2334 - Section 010
 * 2/26/15
 *
 */
public class State implements Comparable<State>
{
	private ArrayList<City> cities;
	private String stateName;
	
	//pre/post conditions for two-letter abbreviation
	//Default Constructor
	public State() //defaults to Oklahoma
	{
		stateName = "OK"; 
		this.cities = new ArrayList<City>();
	}
	
	/**
	 * Constructor with stateName passed in
	 * @param s
	 */
	public State(String s)
	{
		stateName = s;
		this.cities = new ArrayList<City>();
	}
	
	/**
	 * Accessor
	 * @return
	 */
	public ArrayList<City> getCityList()
	{
		return this.cities; //returns the array list of city objects
	}
	
	/**
	 * converts stateName toString
	 */
	public String toString()
	{
		return this.stateName; //returns the name of a state as a STring
	}
	 
	/**
	 * Fills an arraylist ONLY containing cities of same state
	 * @param listOfData
	 * @return
	 */
	public ArrayList<City> fillCityList(ArrayList<Person> listOfData)
	{ 
		
		for (int i=0; i < listOfData.size(); i++)
		{
			if (listOfData.get(i).getStateLOC().equalsIgnoreCase(this.stateName)) //if the states are the same
			{
				String personCityName = listOfData.get(i).getCityLOC();
				State personCityState = new State(listOfData.get(i).getStateLOC());
				City personCity = new City(personCityName, personCityState);
				int count = 0;
				for (int j = 0; j < cities.size(); j++)
				{
					if (cities.get(j).equals(personCity))
						count++;
				}
				if (count == 0)
					cities.add(personCity);
			}
		}
		Collections.sort(this.cities);
		return this.cities;
		
	}
	
	/**
	 * Compares 1 state to another with .equalsIgnoreCase
	 * @param other
	 * @return true if it is same, or false otherwise
	 */
	public boolean equals(State other)
	{
		return this.stateName.equalsIgnoreCase(other.toString());
	}
	
	/**
	 * Compares 1 state to another
	 * @return int (position)
	 */
	public int compareTo(State other)
	{ 
		if (this.stateName.equals(other.toString()))
			return 0;
		else
			return this.stateName.compareTo(other.toString());
	}
}