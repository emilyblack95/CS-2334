import java.io.Serializable;
import java.util.Calendar;

/**
 * Project #3
 * CS 2334, Section 010
 * 3/27/15
 * <P>
 * Creates a date object consisting of a month, day, and year. This class contains methods 
 * to return the date as a Date object or a string as well as return each of the individual 
 * components as integers. The class can also calculate the integer number of years between 
 * two dates. 
 * <P>
 * @version 1.0
 */
public class Date implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -382684161481315131L;

	/** Stores the day of the month as an integer between 1 and 31. */
	private int day;
	
	/** Stores the month as an integer between 1 and 12. */
	private int month;
	
	/** Stores the year as a four-digit integer. */
	private int year;
	
	/**
	 * Default Constructor - defaults to January 1, 1900.
	 */
	public Date(){
		this.day = 1;
		this.month = 1;
		this.year = 1800;
	} //end Date
	
	/**
	 * Constructor - creates a Date object based on the day, month, and year
	 * passed through the parameters. Day of the month is represented as an 
	 * integer between 1 and 12, where 1 is January and 12 is December. Month
	 * is represented as an integer between 1 and 31. Year is represented as a 
	 * 4-digit integer. 
	 * <P>
	 * PRE-CONDITIONS
	 * @param	newMonth	an integer representation of the day of the month 
	 * @param	newDate		the date of the month 
	 * @param	newYear		the four-digit integer representation of the year
	 */
	public Date(int newDate, int newMonth, int newYear){
		day = newDate;
		month = newMonth;
		year = newYear;
	} //end Date
	
	/**
	 * Returns the day of the month of the specific date.
	 * <P>
	 * @return	an integer representation of the day of the month from 1-31
	 */
	public int getDay(){
		return this.day; 
	} //end getDay

	/**
	 * Returns the month of the specific date.
	 * <P>
	 * @return	an integer representation of the month from 1-12
	 * 		 	where 1 is January and 12 is December
	 */
	public int getMonth(){
		return this.month;
	}//end getMonth
	
	/**
	 * Returns the day of the year of the specific date.
	 * <P>
	 * @return	the integer representation of the year of the specific date
	 */
	public int getYear(){
		return this.year;
	} //end getYear
	
	
	/**
	 * Returns the current calendar date as a Date object using the Java Calendar class.
	 * <P>
	 * @return	the current date 
	 */
	public static Date getCurrentDate(){
		Calendar now = Calendar.getInstance(); 
		int nowDay = now.get(Calendar.DATE);
		int nowMonth = now.get(Calendar.MONTH) + 1;
		int nowYear = now.get(Calendar.YEAR); 
		
		return new Date(nowDay, nowMonth, nowYear);
	}//end getCurrentDate
	
	/**
	 * Returns the date represented by the Date object as a String in the 
	 * format DD/MM/YYYY.
	 * <P>
	 * @return	a string representation of the date in the format DD/MM/YYYY
	 */
	public String toString(){
		String newDay;
		String newMonth;
		
		if (this.day < 10)
			newDay = "0" + this.day;
		else
			newDay = Integer.toString(this.day);
		
		if (this.month < 10)
			newMonth = "0" + this.month;
		else
			newMonth = Integer.toString(this.month);
		
		String dateString = newDay + "/" + newMonth + "/" + this.year;
		return dateString;
	}//end dateToString
	
	/**
	 * Determines whether two Date objects refer to the same date by comparing
	 * their day, month, and year values.
	 * <P>
	 * @param 	date2	the Date being compared
	 * @return			true if the Objects have the same values for their instance 
	 * 					fields; false otherwise
	 */
	public boolean equals(Date date2){
		if (this.day == date2.getDay() && this.month == date2.getMonth() &&
				this.year == date2.getYear())
			return true;
		else
			return false;
	} //end equals
	
	/**
	 * Returns the date represented by the given string as a Date object.
	 * <P>
	 * @param	date	a String representing a date in the form DD/MM/YYYY
	 * @return			a Date representation of the given date
	 */
	public static Date stringToDate(String date){
		String[] datePieces = date.split("/"); 
		
		int intDay = Integer.parseInt(datePieces[0]);
		int intMonth = Integer.parseInt(datePieces[1]);		
		int intYear = Integer.parseInt(datePieces[2]);	
		
		Date dateFromString = new Date(intDay, intMonth, intYear);
		return dateFromString;
	} //end stringToDate

	
	/**
	 * Calculates the number of years between two dates, rounded down to the
	 * nearest integer.
	 * <P>
	 * Algorithm: The method calculates the absolute value of the number of years 
	 * in between the two dates rounded down to the nearest integer less than or 
	 * equal to the exact value. 
	 * <P>
	 * @param	date1	the first date for the calculation
	 * @param 	date2	the second date for the calculation
	 * @return 			the integer number of years between the two dates
	 */
	public static int calculateYearsBetween(Date date1, Date date2){ 
		int yearsBetween = date1.getYear() - date2.getYear();

		if (yearsBetween > 0) //Determines if date1 is later.
		{ 
			if (date1.getMonth() == date2.getMonth()) //Determines if both dates have the same month.
			{ 
				if (date1.getDay() < date2.getDay()) //Determines if the day of month hasn't passed yet.
					yearsBetween--;		//Subtracts one from the year to round down.
			} //end if
			else if (date1.getMonth() < date2.getMonth()) //Determines if the month hasn't passed yet.
				yearsBetween--;		
		} //end if
		else if (yearsBetween < 0) //Determines if date2 is later.
		{ 
			yearsBetween *= -1; //Takes the absolute value of the years in between.
			
			if (date1.getMonth() == date2.getMonth()) 
			{ 
				if (date2.getDay() < date1.getDay()) 
					yearsBetween--;		
			} //end if
			else if (date2.getMonth() < date1.getMonth()) 
				yearsBetween--;		
		} //end else if
		return yearsBetween; 
	} 
}