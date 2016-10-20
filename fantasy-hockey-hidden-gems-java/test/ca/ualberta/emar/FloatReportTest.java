package ca.ualberta.emar;

import static org.junit.Assert.assertEquals;

import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class FloatReportTest {

	@Test
	public void testGetLastXGamesAverage(){
		FloatReport fr = new FloatReport("TestStat");
		ArrayDeque<Float> array = new ArrayDeque<Float>(10);
		array.add(1f);
		array.add(2f);
		array.add(3f);
		Float testAvg = fr.getXLastGamesAvg(array, 3);
		DecimalFormat df = new DecimalFormat("0.0000");
		assertEquals("Wrong average", testAvg.equals(2f), true);
	}
	
	@Test
	public void testSort(){
		FloatReport fr = new FloatReport("TestStat");
		FloatReport reverse = new FloatReport("savePctg");
		
		ArrayDeque<Float> array1 = new ArrayDeque<Float>(10);
		array1.add(1f);
		array1.add(2f);
		array1.add(3f);
		
		fr.add("player1", array1);
		reverse.add("player1", array1);
		
		ArrayDeque<Float> array2 = new ArrayDeque<Float>(10);
		array2.add(2f);
		array2.add(3f);
		array2.add(4f);

		fr.add("player2", array2);
		reverse.add("player2", array2);
		
		ArrayDeque<Float> array3 = new ArrayDeque<Float>(10);
		array3.add(3f);
		array3.add(4f);
		array3.add(5f);
		
		fr.add("player3", array3);
		reverse.add("player3", array3);
		
//		Map<Float, List<String>> map1 = fr.sortStats(3);
//		Map<Float, List<String>> map2 = reverse.sortStats(3);
//		
//		for (Entry<Float, List<String>> entry : map1.entrySet()){
//			System.out.println("key: " + entry.getKey());
//			System.out.println("value: " + entry.getValue());
//		}
//		
//		for (Entry<Float, List<String>> entry2: map2.entrySet()){
//			System.out.println("key: " + entry2.getKey());
//			System.out.println("value" + entry2.getValue());
//		}
//		
		
		fr.createReport(3);
		reverse.createReport(3);
	}

}
