/**
 * Project 2
 * CS 2334 - Section 010
 * 2/26/15
 *
 */
public class Person implements Comparable<Person> //Added implement statement //THIS CLASS HAS METHODS COMPARING CITY/STATE 
{
	private Name fullName;
	private String dob;
	private String cityLoc;
	private String stateLoc;
	
	/**
	 * Default Constructor
	 * @param fullName
	 * @param dob
	 * @param cityLoc
	 * @param stateLoc
	 */
	public Person(Name fullName, String dob, String cityLoc, String stateLoc)
	{
		this.fullName = fullName;
		this.dob = dob;
		this.cityLoc = cityLoc;
		this.stateLoc = stateLoc;
	}
	
	/**
	 * Accessor
	 * @return fullName
	 */
	public Name getFullName()
	{
		return fullName;
	}
	
	/**
	 * Accessor
	 * @return dob
	 */
	public String getDOB()
	{
		return dob;
	}
	
	/**
	 * Accessor
	 * @return cityLoc
	 */
	public String getCityLOC()
	{
		return cityLoc;
	}
	
	/**
	 * Accessor
	 * @return stateLoc
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
		String convertedToString = (fullName.toString() + ", " + dob + ", " + cityLoc + ", " + stateLoc);
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
}