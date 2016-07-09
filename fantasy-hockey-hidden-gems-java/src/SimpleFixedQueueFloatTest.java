import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SimpleFixedQueueFloatTest {

	SimpleFixedQueueFloat s;
	
	@Before
	public void setUp() throws Exception {
		s = new SimpleFixedQueueFloat();
	}

	@Test
	public void testEmpty() {
		assertEquals(true, s.empty());
	}
	
	@Test
	public void testSize(){
		s.enqueueFloat(5.0f);
		assertEquals(s.getSize(), 1);
	}
	
	@Test
	public void testEqueueOverCapacity(){
		s.enqueueFloat(1f);
		s.enqueueFloat(2f);
		s.enqueueFloat(3f);
		s.enqueueFloat(4f);
		s.enqueueFloat(5f);
		s.enqueueFloat(6f);
		s.enqueueFloat(7f);
		s.enqueueFloat(8f);
		s.enqueueFloat(9f);
		s.enqueueFloat(10f);
		try {
			s.enqueueFloat(11f);
		} catch (ArrayIndexOutOfBoundsException a) {
			System.out.println("Over capacity exception caught: " + a);
		}
	}
	
	@Test
	public void testDequeueSingle(){
		s.enqueueFloat(5f);
		assertEquals(s.dequeueFloat(), 5f, 0f);
	}
	
	@Test
	public void testOrdering(){
		s.enqueueFloat(1f);
		s.enqueueFloat(2f);
		s.enqueueFloat(3f);
		assertEquals(s.dequeueFloat(), 1f, 0f);
		assertEquals(s.dequeueFloat(), 2f, 0f);
		assertEquals(s.dequeueFloat(), 3f, 0f);
	}
	
	@Test
	public void testToString(){
		s.enqueueFloat(1f);
		s.enqueueFloat(2f);

		System.out.println(s.toString());
	}

}
