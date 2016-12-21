import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class CityTest {

	@Test
	public void test() {
		//tests equals method
		City ou = new City("Norman", new State("OK"));
		City home = new City("Elmhurst", new State("IL"));
		boolean isEqual = ou.equals(home);
		assertEquals(isEqual, false);
				
		City suburb = new City("Norman", new State("OK")); //CHANGED THIS TEST because City() had been changed
		boolean isEqual2 = ou.equals(suburb);
		assertEquals(isEqual2, true);
		
		//tests compareTo method
		City capital = new City("Oklahoma City", new State("OK"));
		int comparison = capital.compareTo(ou);
		boolean test = comparison > 0;
		assertEquals(test, true);
				
		int comparison2 = ou.compareTo(capital);
		boolean test2 = comparison2 < 0;
		assertEquals(test2, true);
		
		int comparison3 = ou.compareTo(suburb);
		boolean test3 = comparison3 == 0;
		assertEquals(test3, true);
		
		//tests fillResidentList method
		City testPlace = new City("Chicago", new State ("IL"));
		State testState = new State("IL");
		Date dob = new Date(23,5,1996);
		ArrayList<Person> personList = new ArrayList<Person>();
		Person a = new Person(new Name("A", "B"), dob, testPlace, testState);
		Person b = new Person(new Name("B", "B"), dob, testPlace, testState);
		Person c = new Person(new Name("C", "B"), dob, testPlace, testState);
		Person d = new Person(new Name("A", "B"), dob, capital, new State("OK"));
		Person e = new Person(new Name("A", "B"), dob, new City("Elmhurst", testState), testState);

		personList.add(a);
		personList.add(b);
		personList.add(c);
		personList.add(d);
		personList.add(e);
		
		int personSize = personList.size();
		testPlace.fillResidentList(personList);
		int resSize = testPlace.getResidentList().size();

		assertEquals(personSize, 5);
		assertEquals(resSize, 3);
		
		//tests how the constructors handle latitude & longitude
		City norman = new City("Norman", new State("OK"), 35.220389, -97.457743);
		int lat = norman.getLatitude();
		int lon = norman.getLongitude();
		assertEquals(lat, 35);
		assertEquals(lon, 97);
				
     
	}
}