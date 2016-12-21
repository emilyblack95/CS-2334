/**
 * Project 2
 * CS 2334 - Section 010
 * 2/26/15
 *
 */
public class Name  
{
	private String firstName;
	private String middleName;
	private String secondMiddleName;
	private String thirdMiddleName;
	private String lastName;
	
	/**
	 * Default Constructor
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
	 * Constructor that assumes person has no middle name
	 * @param firstName
	 * @param lastName
	 */
	public Name(String firstName, String lastName)
	{
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	/**
	 * Constructor that assumes person has 2 middle names
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
	 * Constructor that assumes person has 3 middle names
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
	 * Accessor
	 * @return firstName
	 */
	public String getFirstName()
	{
		return firstName;
	}
	
	/**
	 * Accessor
	 * @return middleName
	 */
	public String getMiddleName()
	{
		return middleName;
	}
	
	/**
	 * Accessor
	 * @return middleName
	 */
	public String getSecondMiddleName()
	{
		return secondMiddleName;
	}
	
	/**
	 * Accessor
	 * @return middleName
	 */
	public String getThirdMiddleName()
	{
		return thirdMiddleName;
	}
	
	/**
	 * Accessor
	 * @return lastName
	 */
	public String getLastName()
	{
		return lastName;
	}
	
	/**
	 * Converts a persons fullname to a string
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