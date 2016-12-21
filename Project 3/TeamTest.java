import static org.junit.Assert.*;

import org.junit.Test;


public class TeamTest {

	@Test
	public void test() {
		
		//tests compareTo method
		State ouState = new State("OK");
		City ouCity = new City("Norman", ouState);
		Team sooners = new Team("Oklahoma Sooners", ouCity, ouState, null);
		
		State rivalState = new State("TX");
		City rivalCity = new City("Dallas", rivalState);
		Team rivals = new Team("Texas Longhorns", rivalCity, rivalState, null);
		int value1 = rivals.compareTo(sooners);
		boolean test1 = value1 > 0;
		assertEquals(test1, true);
		
		int value2 = sooners.compareTo(rivals);
		boolean test2 = value2 < 0;
		assertEquals(test2, true);
		
		Team soonerBasketball = new Team("Oklahoma Sooners", ouCity, ouState, null);
		int value3 = soonerBasketball.compareTo(sooners);
		boolean test3 = value3 == 0;
		assertEquals(test3, true);
		
		
	}

}


