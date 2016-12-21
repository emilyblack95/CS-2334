import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.io.IOException;

public class CityTest {

	@Test
	public void test() throws IOException{
		
		//tests equals method
		City ou = new City("Norman", new State("OK"));
		City home = new City("Elmhurst", new State("IL"));
		boolean isEqual = ou.equals(home);
		assertEquals(isEqual, false);
		
		City suburb = new City(); //default constructor is Norman, OK
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
		City testCity = new City("Chicago", new State("IL"));
		ArrayList<Person> listOfData = Driver.fillArrayList("people.csv");
		ArrayList<Person> resList = testCity.fillResidentList(listOfData);
		
		if (resList != null)
		{
			System.out.println(resList.toString());
			System.out.println(resList.size());
		}
			
		boolean hasData = resList != null;
		assertEquals(hasData, true);
		
		
	}

}
