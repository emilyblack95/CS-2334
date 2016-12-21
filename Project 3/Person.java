import java.io.Serializable;

/**
 * Project 3
 * CS 2334 - Section 010
 * 2/26/15
 *
 * This class models a person who has a full name, birthday, and hometown (city, state).
 * 
 */
public class Person implements Comparable<Person>, Serializable {
	
	private static final long serialVersionUID = 1L;  //generates serialVersionUID for the class to be serializable
	private Name fullName;
	private Date dob;
	private String cityLoc;
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
}