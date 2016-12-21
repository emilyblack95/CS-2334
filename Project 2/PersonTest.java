import static org.junit.Assert.*;

import org.junit.Test;


public class PersonTest {

	@Test
	public void test() {		
		//tests compareTo method
		Person personA = new Person(new Name("Taylor", "Swift"), "13/12/1989", "Reading", "PA");
		Person personB = new Person(new Name("Taylor", "Lautner"), "11/02/1992", "Los Angeles", "CA");
		
		int comp = personA.compareTo(personB);
		boolean testComp = comp > 0;
		assertEquals(testComp, true); //positive if personA's full name comes after personB's
		
		Person personC = new Person(new Name("Taylor", "Swift"), "04/02/1996", "Chicago", "IL");
		int comp2 = personA.compareTo(personC);
		boolean testComp2 = comp2 == 0;
		assertEquals(testComp2, true); //0 if their full names are equal
		
		Person personD = new Person(new Name("Justin", "Timberlake"), "31/01/1981", "Los Angeles", "CA");
		int comp3 = personA.compareTo(personD);
		boolean testComp3 = comp3 > 0;
		assertEquals(testComp3, true); //positive if personA's full name comes after the second person's
				
		//tests toString method
		String personString = personA.toString();
		assertEquals(personString, "Taylor Swift, 13/12/1989, Reading, PA");
	}

}
