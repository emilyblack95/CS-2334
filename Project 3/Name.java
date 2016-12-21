import java.io.Serializable;

/**
 * Project 3
 * CS 2334 - Section 010
 * 2/26/15
 *
 * This class represents a person's name. This name will have a first and last name and up to three middle names.
 */
public class Name implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1310967130582551023L;
	private String firstName;
	private String middleName;
	private String secondMiddleName;
	private String thirdMiddleName;
	private String lastName;
	
	/**
	 * Constructor that assumes person has one middle name.
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 */
	public Name(String firstName, String middleName, String lastName)
	{
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}
	
	/**
	 * Constructor that assumes person has no middle name.
	 * @param firstName
	 * @param lastName
	 */
	public Name(String firstName, String lastName)
	{
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	/**
	 * Constructor that assumes person has 2 middle names.
	 * @param firstName
	 * @param middleName
	 * @param secondMiddleName
	 * @param lastName
	 */
	public Name(String firstName, String middleName, String secondMiddleName, String lastName)
	{
		this.firstName = firstName;
		this.middleName = middleName;
		this.secondMiddleName = secondMiddleName;
		this.lastName = lastName;
	}
	/**
	 * Constructor that assumes person has 3 middle names.
	 * @param firstName		
	 * @param middleName
	 * @param secondMiddleName
	 * @param thirdMiddleName
	 * @param lastName
	 */
	public Name(String firstName, String middleName, String secondMiddleName, String thirdMiddleName, String lastName)
	{
		this.firstName = firstName;
		this.middleName = middleName;
		this.secondMiddleName = secondMiddleName;
		this.thirdMiddleName = thirdMiddleName;
		this.lastName = lastName;
	}
	
	/**
	 * Returns the person's first name as a String.
	 * @return first name
	 */
	public String getFirstName()
	{
		return firstName;
	}
	
	/**
	 * Returns the person's  middle name as a String.
	 * @return middle name
	 */
	public String getMiddleName()
	{
		return middleName;
	}
	
	/**
	 * Returns the person's second middle name as a String.
	 * @return middle name
	 */
	public String getSecondMiddleName()
	{
		return secondMiddleName;
	}
	
	/**
	 * Returns the person's third middle name as a String.
	 * @return middle name
	 */
	public String getThirdMiddleName()
	{
		return thirdMiddleName;
	}
	
	/**
	 * Returns the person's last name as a String
	 * @return last name
	 */
	public String getLastName()
	{
		return lastName;
	}
	
	/**
	 * Converts a person's full name to a string.
	 * @return 	string representation of full name
	 */
	public String toString()
	{
		String fullName;
		if(thirdMiddleName == null && secondMiddleName == null && middleName == null)       //if they have no middle name at all
		{
			fullName = firstName + " " + lastName;
		}
		else if(thirdMiddleName == null && secondMiddleName == null)   //if they have 1 middle name
		{
			fullName = firstName + " " + middleName + " " + lastName;
		}
		else if(thirdMiddleName == null)   //if they have 2 middle names
		{
			fullName = firstName + " " + middleName + " " + secondMiddleName + " " + lastName;
		}
		else 
		{
			fullName = firstName + " " + middleName + " " + secondMiddleName + " " + thirdMiddleName + " " + lastName;
		}
		return fullName;
	}
}