import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SimpleFixedQueueTest {
	
	SimpleFixedQueue s;

	@Before
	public void setUp() throws Exception {
		s = new SimpleFixedQueue();
	}

	@Test
	public void testEmpty() {
		assertEquals(s.empty(), true);
	}
	
	@Test
	public void testGetSize(){
		assertEquals(s.getSize(), 0);
	}

	@Test
	public void testEqueueOverCapacity(){
		s.enqueue(1);
		s.enqueue(2);
		s.enqueue(3);
		s.enqueue(4);
		s.enqueue(5);
		s.enqueue(6);
		s.enqueue(7);
		s.enqueue(8);
		s.enqueue(9);
		s.enqueue(10);
		try {
			s.enqueue(11);
		} catch (ArrayIndexOutOfBoundsException a) {
			System.out.println("Over capacity exception caught: " + a);
		}
	}
	
	@Test
	public void testDequeueSingle(){
		s.enqueue(5);
		assertEquals(s.dequeue(), 5);
	}
	
	@Test
	public void testOrdering(){
		s.enqueue(1);
		s.enqueue(2);
		s.enqueue(3);
		assertEquals(s.dequeue(), 1);
		assertEquals(s.dequeue(), 2);
		assertEquals(s.dequeue(), 3);
	}
}
