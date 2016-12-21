/**
 * @author Emily Black CS 2334
 * @version 1.0
 */
public class Person 
{
	private static int errorCount;
	private String fullName;
	private Date dateOfBirth;
	private String cityOfBirth;
	private String stateOfBirth;
	private Date dateOfDeath;
	private int ageFinal;
	
	
	/** This is the default constructor ASSUMING the person is still alive.
	 * @param String fullName
	 * @param Date dateOfBirth
	 * @param String cityOfBirth
	 * @param String stateOfBirth
	 */
	public Person(String fullName, Date dateOfBirth, String cityOfBirth, String stateOfBirth)
	{
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.cityOfBirth = cityOfBirth;
		this.stateOfBirth = stateOfBirth;
	}
	
	/**
	 * This constructor consists of their current age.
	 * @param fullName
	 * @param dateOfBirth
	 * @param cityOfBirth
	 * @param stateOfBirth
	 * @param age
	 */
	public Person(String fullName, Date dateOfBirth, String cityOfBirth, String stateOfBirth, int ageFinal)
	{
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.cityOfBirth = cityOfBirth;
		this.stateOfBirth = stateOfBirth;
		this.ageFinal = ageFinal;
	}
	
	/** This constructor assumes the person is dead.
	 * @param String fullName
	 * @param Date dateOfBirth
	 * @param String cityOfBirth
	 * @param String stateOfBirth
	 * @param Date dateOfDeath
	 */
	public Person(String fullName, Date dateOfBirth, String cityOfBirth, String stateOfBirth, Date dateOfDeath)
	{
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.cityOfBirth = cityOfBirth;
		this.stateOfBirth = stateOfBirth;
		this.dateOfDeath = dateOfDeath;
	}
	
	/**
	 * This constructor consists of their death age.
	 * @param fullName
	 * @param dateOfBirth
	 * @param cityOfBirth
	 * @param stateOfBirth
	 * @param dateOfDeath
	 * @param age
	 */
	public Person(String fullName, Date dateOfBirth, String cityOfBirth, String stateOfBirth, Date dateOfDeath, int ageFinal)
	{
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.cityOfBirth = cityOfBirth;
		this.stateOfBirth = stateOfBirth;
		this.dateOfDeath = dateOfDeath;
		this.ageFinal = ageFinal;
	}
	
	/**
	 * ACCESSOR.
	 * @return fullName
	 */
	public String getFullName()
	{
		return fullName;
	}
	
	/**
	 * ACCESSOR.
	 * @return dateOfBirth
	 */
	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}
	
	/**
	 * ACCESSOR.
	 * @return cityOfBirth
	 */
	public String getCityOfBirth()
	{
		return cityOfBirth;
	}
	
	/**
	 * ACCESSOR.
	 * @return stateOfBirth
	 */
	public String getStateOfBirth()
	{
		return stateOfBirth;
	}
	
	/**
	 * ACCESSOR.
	 * @return dateOfDeath
	 */
	public Date getDateOfDeath()
	{
		return dateOfDeath;
	}
	
	/**
	 * CONVERTS OBJECT TYPE PERSON TO STRING
	 * @return results
	 */
	public String toString()
	{
		String results = null;
		if(dateOfDeath == null)  //they are alive
		{
			results = (fullName + ", " + dateOfBirth.toString() + ", " + cityOfBirth + ", " + stateOfBirth + ", " + ageFinal);
		}
		else if(dateOfDeath != null)  //they are dead
		{
			results = (fullName + ", " + dateOfBirth.toString() + ", " + cityOfBirth + ", " + stateOfBirth + ", " + dateOfDeath.toString() + ", " + ageFinal);
		}
		return results;
	}
	
	/**
	 * This method calls on other methods from the Date class and calculates age(deceased or alive)
	 * (NOTE): This could of been done with &&/|| statements.
	 * @returns age
	 */
	public static int calculateCurrentAge(Date DOB)
	{
			int currentDay = 5;  //the 1st
			int currentMonth = 2;  //February
			int ageFinal = 0;
			if(DOB.getMonth() > currentMonth)  //if they havent had their birthday
			{
				ageFinal = DOB.setCurrentAge(DOB);
			}
			else if(DOB.getMonth() < currentMonth) //if they have had their birthday
			{
				if(DOB.getDay() > currentDay)  //if they have had their birthday
				{
					ageFinal = DOB.setCurrentAge(DOB) - 1;
				}
				else if(DOB.getDay() < currentDay)  //if they havent had their birthday
				{
					ageFinal = DOB.setCurrentAge(DOB);
				}
				else if(DOB.getDay() == currentDay)  //if it is their birthday today
				{
					ageFinal = DOB.setCurrentAge(DOB);
				}
			}
			else if(DOB.getMonth() == currentMonth)
			{
				if(DOB.getDay() > currentDay)  //if they have had their birthday
				{
					ageFinal = DOB.setCurrentAge(DOB) - 1;
				}
				else if(DOB.getDay() < currentDay)  //if they havent had their birthday
				{
					ageFinal = DOB.setCurrentAge(DOB);
				}
				else if(DOB.getDay() == currentDay)  //if it is their birthday today
				{
					ageFinal = DOB.setCurrentAge(DOB);
				}
			}
		return ageFinal;	
	}
	
	/**
	 * This method calls on other methods from the Date class and calculates age(deceased or alive)
	 * (NOTE): This could of been done with &&/|| statements.
	 * @returns age
	 */
	public static int calculateDeathAge(Date DOB, Date DOD)
	{
		int ageFinal = 0;
		if(DOB.getYear() > DOD.getYear())
		{
			errorCount++;
		}
		if(DOD.getMonth() > DOB.getMonth()) //if they died past their birthday
		{
			ageFinal = DOB.setDeathAge(DOB, DOD);
		}
		else if(DOD.getMonth() < DOB.getMonth())  //if they didnt die past their birthday
		{
			ageFinal = DOB.setDeathAge(DOB, DOD) - 1;
		}
		else if(DOD.getMonth() == DOB.getMonth())  //if they died in the same month as their birthday
		{
			if(DOD.getDay() > DOB.getDay())  //if they died after their birthday
			{
				ageFinal = DOB.setDeathAge(DOB, DOD);
			}
			else if(DOD.getDay() < DOB.getDay())  //if they died before their birthday
			{
				ageFinal = DOB.setDeathAge(DOB, DOD) - 1;
			}
			else if(DOD.getDay() == DOB.getDay())  //if somehow they died exactly on their birthday
			{
				ageFinal = DOB.setDeathAge(DOB, DOD);
			}
		}
		return ageFinal;
	}
	
	public static void getNumberOfErrors()
	{
		System.out.println("Number of DOB year > DOD year ERRORS: " + errorCount);
	}
	
}
