import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Project 5
 * CS 2334 - Section 010
 * 4/29/15
 */
public class State implements Comparable<State>, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 39269608675307449L;
	private ArrayList<City> cities;
	private String stateName;
	
	/**
	 * Default constructor - defaults to Oklahoma (OK)
	 */
	public State() 
	{
		stateName = "OK"; 
		this.cities = new ArrayList<City>();
	}
	
	/**
	 * Constructor with stateName passed in.
	 * @param s	name of state
	 */
	public State(String s)
	{
		stateName = s;
		this.cities = new ArrayList<City>();
	}
	
	/**
	 * Returns the array list of cities contained in the state.
	 * @return	list of cities
	 */
	public ArrayList<City> getCityList()
	{
		return this.cities; //returns the array list of city objects
	}
	
	/**
	 * Converts state name to a String object
	 * @return name of state
	 */
	public String toString()
	{
		return this.stateName; //returns the name of a state as a STring
	}
	 
	/**
	 * Fills an arraylist ONLY containing cities of same state.
	 * @param listOfData	list of people from which the cities will be filled
	 * @return				list of cities in the specified state
	 */
	public ArrayList<City> fillCityList(ArrayList<Person> listOfData)
	{ 
		
		for (int i=0; i < listOfData.size(); i++)
		{
			if (listOfData.get(i).getState().toString().equalsIgnoreCase(this.stateName)) //if the states are the same
			{
				String personCityName = listOfData.get(i).getCity().toString();
				State personCityState = new State(listOfData.get(i).getState().toString());
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
	 * Compares one state to another by their names.
	 * @param other		the state being compared to
	 * @return			true if it is same, or false otherwise
	 */
	public boolean equals(State other)
	{
		return this.stateName.equalsIgnoreCase(other.toString());
	}
	
	/**
	 * Compares one state to another alphabetically by name.
	 * @return 		0 if the states are equal, a positive integer if this state comes after the other state, or
	 * 				a negative integer if this state comes before the other state
	 */
	public int compareTo(State other)
	{ 
		if (this.stateName.equals(other.toString()))
			return 0;
		else
			return this.stateName.compareTo(other.toString());
	}
}