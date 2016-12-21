import static org.junit.Assert.*;

import org.junit.Test;


public class StateTest {

	@Test
	public void test() {
		//tests equals method
		State biggest = new State("AK");
		State smallest = new State("RI");
		boolean isEqual = biggest.equals(smallest);
		assertEquals(isEqual, false);
				
		State alaska = new State("AK");
		boolean isEqual2 = alaska.equals(biggest);
		assertEquals(isEqual2, true);
				
		//tests compareTo method				
		int comparison = biggest.compareTo(smallest);		
		boolean test = comparison < 0;	
		assertEquals(test, true);
				
		int comparison2 = smallest.compareTo(biggest);
		boolean test2 = comparison2 > 0;
		assertEquals(test2, true);
				
		int comparison3 = alaska.compareTo(biggest);
		boolean test3 = comparison3 == 0;
		assertEquals(test3, true);	
		
	}
}

