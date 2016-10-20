package ca.ualberta.emar;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

public class IntegerReportTest {


	@Test
	public void testGetLastMethods(){
		ArrayDeque<Integer> a = new ArrayDeque<Integer>();
		a.add(10);
		a.add(20);
		a.add(5);
		a.add(90);
		a.add(1);
		IntegerReport r = new IntegerReport("TestStat");
		int count = r.getLastXGamesTotal(a, 3);
		assertEquals("Not equal", count, 35);
		assertEquals(a.size(), 5);
		count = r.getLastXGamesTotal(a, 5);
		assertEquals(count, 126);
	}
	
	
	@Test
	public void testCreateReport(){
		IntegerReport ir = new IntegerReport("TestStat");
		//create 3 different queues
		ArrayDeque<Integer> a1, a2, a3;
		a1 = new ArrayDeque<Integer>();
		a2 = new ArrayDeque<>();
		a3 = new ArrayDeque<>();
		int[] t1 = {1, 2, 3, 4};
		int[] t2 = {5, 6, 7};
		int[] t3 = {8, 9, 10};
		for (int i = 0; i < t1.length; i++){
			a1.add(t1[i]);
		}
		for (int j = 0; j < t2.length; j++){
			a2.add(t2[j]);
		}
		for (int k = 0; k < t3.length; k++){
			a3.add(t3[k]);
		}
		//rank should go player3, player 1 & 4, player2
		ir.add("player3", a3);
		ir.add("player2", a1);
		ir.add("player1", a2);
		ir.add("player4", a2);
		ir.createReport(3);
		
	}
	
	@Test
	public void testSortStats(){
		ArrayDeque<Integer> a = new ArrayDeque<Integer>(10);
		a.add(1);
		a.add(3);
		a.add(9);
		
		Integer[] b = a.toArray(new Integer[3]);
		System.out.println(Arrays.toString(b));
	}
}
