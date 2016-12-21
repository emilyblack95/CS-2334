import static org.junit.Assert.*;

import org.junit.Test;


public class CompareLastNameTest {

	@Test
	public void test() 
	{
		CompareLastName c = new CompareLastName();
		
		Person personA = new Person(new Name("Taylor", "Swift"), new Date(13, 12, 1989), "City", "State");
		Person personB = new Person(new Name("Andrea", "Swift"), new Date(13, 12, 1960), "City", "State");
		
		int comp = c.compare(personA, personB);
		boolean newTest = comp > 0; 
		assertEquals(newTest, true); //positive if their last names are equal but personA's first name comes second
		
		int comp4 = c.compare(personB, personA);
		boolean newTest2 = comp4 < 0;
		assertEquals(newTest2, true); //negative if their last names are equal but person B's first name comes first
		
		Person personC = new Person(new Name("Miley", "Cyrus"), new Date(23, 11, 1992), "City", "State");
		int comp2 = c.compare(personA, personC);
		boolean newTest3 = comp2 > 0;
		assertEquals(newTest3, true); //positive if personA's last name comes after the second person's
		
		Person personD = new Person(new Name("Justin", "Timberlake"), new Date(31, 01, 1981), "City", "State");
		int comp3 = c.compare(personA, personD);
		boolean newTest4 = comp3 < 0;
		assertEquals(newTest4, true); //negative if personA's last name comes before the second person's
		}

}