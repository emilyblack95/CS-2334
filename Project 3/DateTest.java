import static org.junit.Assert.*;

import org.junit.Test;


public class DateTest {

	@Test
	public void test() {
			
		
				//tests calculateYearsBetween method
				Date day1 = new Date(23, 5, 1996);
				Date day2 = new Date(4, 2, 2015);
				int age = Date.calculateYearsBetween(day1, day2);
				assertEquals(age, 18);
				
			
				
				Date day3 = new Date(31, 12, 3990);
				Date day4 = new Date(1, 1, 1803);
				int difference = Date.calculateYearsBetween(day3, day4);
				assertEquals(difference, 2187);
				
				
				Date day5 = new Date(4, 7, 1776);
				Date day6 = new Date(4, 7, 1776);
				int difference56 = Date.calculateYearsBetween(day5, day6);
				assertEquals(difference56, 0);
				
				//Tests dateToString method
				Date day7 = new Date(8, 5, 2018);
				String graduation = day7.toString();
				assertEquals(graduation, "08/05/2018");
				
				Date day8 = new Date(25, 12, 1952);
				String christmas = day8.toString();
				assertEquals(christmas, "25/12/1952");
				
			
				Date currentDate = Date.getCurrentDate();
				assertEquals(currentDate.toString(), "05/03/2015"); //change to actual current date when testing
								
		
				//Tests stringToDate method
				String day9String = "14/02/2015";
				Date day9 = Date.stringToDate(day9String);
				Date day9Test = new Date(14, 2, 2015);
				assertEquals(day9.equals(day9Test), true);
				
				String day10String = "11/11/1911";
				Date day10 = Date.stringToDate(day10String);
				Date day10Test = new Date(11, 11, 1911);
				assertEquals(day10.equals(day10Test), true);
				
				
				//Tests equals method
				Date day11 = new Date(13,12,1989);
				Date day12 = new Date(13,12,1988);
				assertEquals(day11.equals(day12), false);
				
			}
	}



