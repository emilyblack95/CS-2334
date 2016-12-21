import static org.junit.Assert.*;

import org.junit.Test;


public class PersonTest {

	@Test
	public void test() 
	{
		
			//tests compareTo method
			Date joeBirthday = new Date(1,2,2003);
			Person joeS = new Person(new Name("Joe", "Smith"), joeBirthday, "Norman", "OK");
			Person other = new Person(new Name("Joe", "Smith"), joeBirthday, "Norman", "OK");
			int value = joeS.compareTo(other);		
			boolean samePerson = value == 0;
			assertEquals(samePerson, true);
				
			Date bobBirthday = new Date(4,5,1966);
			Person bobS = new Person(new Name("Bob", "Smith"), bobBirthday, "Norman", "OK");
			int value2 = bobS.compareTo(joeS);
			boolean testValue = value2 < 0;
			assertEquals(testValue, true);
			
			Person newOther2 = new Person(new Name("Taylor", "Swift"), new Date(12,13,1989), "Seattle", "WA");
			int value3 = newOther2.compareTo(joeS);		
			boolean testValue3 = value3 > 0;
			assertEquals(testValue3, true);
				
			//tests calculateCurrentAge method
			Person a = new Person(new Name("A", "Person"), new Date(1,1,2000), "City", "ST"); //CHANGED because it wasn't working...?
			int aAge = a.calculateCurrentAge();
			assertEquals(aAge, 15);
				
			int testAge = newOther2.calculateCurrentAge();
			assertEquals(testAge, 25);
				
		}
}