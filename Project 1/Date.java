/**
 * @author Emily Black CS 2334
 * @version 1.0
 */
public class Date 
{
	private int month;
	private int day;
	private int year;
	private int currentAge;
	
	
	/** 
	 * DEFAULT CONSTRUCTOR. SET MONTH/DAY/YEAR EQUAL TO ZERO.
	 */
	public Date()
	{
		month = 0;
		day = 0;
		year = 0;
	}
	
	/** 
	 * CONSTRUCTOR. Order: MONTH/DAY/YEAR
	 */
	public Date(int month, int day, int year)
	{
		this.month=month;
		this.day=day;
		this.year=year;
	}
	
	/**
	 * ACCESSOR.
	 * @return year
	 */
	public int getYear()
	{
		return year;
	}
	
	/**
	 * ACCESSOR.
	 * @return day.
	 */
	public int getDay()
	{
		return day;
	}
	
	/**
	 * ACCESSOR.
	 * @return month
	 */
	public int getMonth()
	{
		return month;
	}
	
	/**
	 * TAKES OBJECT TYPE DATE AND TURNS IT INTO A STRING.
	 */
	public String toString()
	{
		String result = (month + "/" + day + "/" + year);
		return result;
	}
	
	/**
	 * MUTATOR. METHOD CALLED IN PERSON CLASS.
	 * @param DOB
	 * @return currentAge
	 */
	public int setCurrentAge(Date DOB)
	{
		currentAge = 2015 - DOB.getYear();  //current year is 2015
		return currentAge;
	}
	
	/**
	 * MUTATOR. METHOD CALLED IN PERSON CLASS.
	 * @param DOB
	 * @param DOD
	 * @return currentAge
	 */
	public int setDeathAge(Date DOB, Date DOD)
	{
		currentAge = DOD.getYear() - DOB.getYear();
		return currentAge;
	}
	
	/**
	 * STATIC METHOD USED IN DRIVER CLASS. CONVERTS STRING TO OBJECT TYPE DATE.
	 * @param input
	 * @return output
	 */
	public static Date toDate(String input)
	{
		String[] splitData;
		splitData = input.split("/");
		int month = Integer.parseInt(splitData[0]);
		int day = Integer.parseInt(splitData[1]);
		int year = Integer.parseInt(splitData[2]);
		Date output = new Date(month, day, year);
		return output;
	}
}
