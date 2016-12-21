import static org.junit.Assert.*;

import org.junit.Test;


public class NameTest {

	@Test
	public void test() {
		
		Name spiderman = new Name("Peter", "Parker");
		String s = spiderman.toString();
		assertEquals(s, "Peter Parker");
		
		Name actress = new Name("Sarah", "Jessica", "Parker");
		s = actress.toString();
		assertEquals(s, "Sarah Jessica Parker");
		
		Name middle2 = new Name("First", "Middle1", "Middle2", "Last");
		s = middle2.toString();
		assertEquals(s, "First Middle1 Middle2 Last");
		
		Name middle3 = new Name("First", "Middle1", "Middle2", "Middle3", "Last");
		s = middle3.toString();
		assertEquals(s, "First Middle1 Middle2 Middle3 Last");
		
	}

}
