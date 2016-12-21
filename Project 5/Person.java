import java.io.Serializable;
import java.util.*;

/**
 * Project 5
 * CS 2334 - Section 010
 * 4/29/15
 *
 * This class models a person who has a full name, birthday, and hometown (city, state).
 * 
 */
public class Person implements Comparable<Person>, Serializable 
{
	
	private static final long serialVersionUID = 1L;  //generates serialVersionUID for the class to be serializable
	private Name fullName;
	private Date dob;
	private String cityLoc;
	private static List<Person> neighbors;
	private String stateLoc;
	private City myCity;
	private State myState;
	
	/**
	 * Constructor using String names of city and state.
	 * @param fullName	full name of person
	 * @param dob		birthday of person
	 * @param cityLoc	name of person's city
	 * @param stateLoc	name of person's state
	 */
	public Person(Name fullName, Date dob, String cityLoc, String stateLoc)
	{
		this.fullName = fullName;
		this.dob = dob;
		this.cityLoc = cityLoc;
		this.stateLoc = stateLoc;
		Person.neighbors = new ArrayList<Person>();  //each person object has their own neighbors list
	}
	
	/**
	 * Constructor using City and State objects.
	 * @param fullName	name of person
	 * @param dob		birthday of person
	 * @param city		city where person is from
	 * @param state		state where person is from
	 */
	public Person(Name fullName, Date dob, City city, State state)
	{
		this.fullName = fullName;
		this.dob = dob;
		this.myCity = city;
		this.myState = state;
		Person.neighbors = new ArrayList<Person>();  //each person object has their own neighbors list
	}
	
	/**
	 * Return the person's full name.
	 * @return fullName		full name of the person
	 */
	public Name getFullName()
	{
		return fullName;
	}
	
	/**
	 * Returns the person's birthday as a Date object.
	 * @return dob	the person's date of birth
	 */
	public Date getDOB()
	{
		return dob;
	}
	
	
	/**
	 * Returns the person's city as a City object.
	 * @return myCity	city where person is from
	 */
	public City getCity()
	{
		return myCity;
	}
	
	/**
	 * Returns the person's state as a State object.
	 * @return myState	state where person is from
	 */
	public State getState()
	{
		return myState;
	}
	
	/**
	 * Returns the name of the person's city as a String.
	 * @return 	name of city where person is from
	 */
	public String getCityLOC()
	{
		return cityLoc;
	}
	
	/**
	 * Returns the name of the person's state as a String.
	 * @return 	name of state where person is from
	 */
	public String getStateLOC()
	{
		return stateLoc;
	}
	
	/**
	 * Takes an object of type person and slams it into one string
	 * @return String
	 */
	public String toString()
	{
		String convertedToString = (fullName.toString() + ", " + dob + ", " + myCity.toString() + ", " + myState.toString());
		return convertedToString;
	}
	
	
	/**
	 * This method compares one Person object to the other alphabetically by first name.
	 * <P>
	 * @param 	other	The person this object is being compared to.
	 * @return			a negative integer if this object comes before other, a positive integer
	 * 					if this object comes after other, 	or 0 if the objects are the same.
	 */
	public int compareTo(Person other)
	{		
		String lastName1 = this.fullName.getLastName().toUpperCase();
	    String firstName1 = this.fullName.getFirstName().toUpperCase();
	    String lastName2 = other.getFullName().getLastName().toUpperCase();
	    String firstName2 = other.getFullName().getFirstName().toUpperCase();
	    if (!(firstName1.equals(firstName2)))
	    {
	    	return firstName1.compareTo(firstName2);
	    }
	    else
	    {
	    	return lastName1.compareTo(lastName2);
	    }
	}
	
	/**
	 * Calculates the current age of the person.
	 * @return		the current integer age of the person
	 */
	public int calculateCurrentAge() 
	{
		Date currentDate = Date.getCurrentDate();
		int currentAge = Date.calculateYearsBetween(this.dob, currentDate);
		return currentAge;
	} 
	
	/**
	 * NEW
	 * This method calculates shortest path of another player
	 * @param p: other person being compared
	 * @return Stack<Person>:  returns the list of the list of the shortest paths of a player
	 */
	public static Stack<Person> shortestPath(Person p1, Person p2)
	{
		LinkedList<Stack<Person>> pathsToVisit = new LinkedList<Stack<Person>>();
		Stack<Person> initialPath = new Stack<Person>();
		Stack<Person> currentPath;
		initialPath.add(p2);
		pathsToVisit.add(initialPath);
		currentPath = initialPath;
		while(!pathsToVisit.isEmpty())
		{
			currentPath = pathsToVisit.remove();
			//if(currentPath.peek().equals(p1))
			
			if(p1 == p2)  //if we are comparing the same person (base case)
			{
				break;
			}
			
			currentPath.peek();
			for(Person n : Person.getNeighbors()) //gets neighbors (i set everyone as a neighbor)
			{
				Stack<Person> nextPath = new Stack<Person>();
				nextPath.addAll(currentPath);
				nextPath.push(n);
				pathsToVisit.add(nextPath);
			}
		}
		return currentPath;
	}
	
	/**
	 * NEW
	 * Accessor
	 * @return neighbors
	 */
	public static List<Person> getNeighbors()
	{	
		return neighbors;
	}	
	
	/**
	 * NEW
	 * Mutator
	 * @param anotherPerson
	 */
	public static void addNeighbor(Person p1)
	{
		neighbors.add(p1);
	}
	
	/**
	 * NEW
	 * Mutator
	 * IF THEY PLAYED FOR SAME TEAM, BUT NOT THE SAME YEAR
	 * If the person P isnt on the seasons roster given, add them to neighbors.
	 * @param season s
	 */
	public void addSeason(Season s, Person p)
	{
		for(Person x : s.getSeasonRoster())
		{
			if(x != p)  //changed this to P
			{
				neighbors.add(x);
			}
		}
	}
}
	