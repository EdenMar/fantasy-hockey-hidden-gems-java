package ca.ualberta.emar;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class SkaterTest {


	/*
	 * This test checks if the data from the test can be properly instantiated in an instance
	 * of Skater. 
	 */
	@Test
	public void testConstructorWhenNewToDB() throws Exception, IOException, ParseException{
		Skater skater;

		File f = new File("test/Aaron Ekblad Daily Stats 1.json");
		JSONParser p = new JSONParser();
		JSONObject o = (JSONObject)p.parse(new FileReader(f));
		skater = new Skater(o, ExistsInDB.NO);

		assertEquals("Name wrong", "Aaron Ekblad", skater.getName());
		assertEquals("Games played wrong", 78, skater.getTotalGamesPlayed());
		ArrayDeque<Integer> pM = new ArrayDeque<Integer>(10);
		pM.add(18);
		assertArrayEquals(pM.toArray(), skater.getPlusMinus().toArray());
		assertEquals(15, skater.getTotalGoals());
		ArrayDeque<Integer> g = new ArrayDeque<Integer>(10);
		g.add(15);
		assertArrayEquals(g.toArray(), skater.getGoals().toArray());
		
	}
	
	@Test
	public void testConstructorWhenDBExists() throws Exception, IOException, ParseException{
		Skater skater;
		File f = new File("test/Connor McDavid.json");
		JSONParser p = new JSONParser();
		JSONObject o = (JSONObject)p.parse(new FileReader(f));
		skater = new Skater(o, ExistsInDB.YES);
		
		
		assertEquals("Goals wrong", skater.getTotalGoals(), 16);
		ArrayDeque<Integer> a = new ArrayDeque<Integer>(10);
		a.add(32);
		assertArrayEquals("Assists wrong", a.toArray(), skater.getAssists().toArray());
		
		assertEquals("shGoals wrong", 0, skater.getshGoals());
		
	}
	
//	@Test
	public void testFromJSONAndBackAgain() throws IOException, ParseException{
		Skater skaterInitial;
		File f = new File("test/Connor McDavid.json");
		JSONParser parser = new JSONParser();
		JSONObject object = (JSONObject)parser.parse(new FileReader(f));
		skaterInitial = new Skater(object, ExistsInDB.YES);
		TemporaryFolder folder = new TemporaryFolder();
		File subfolder = folder.newFolder("subfolder");
		File fOut = new File(subfolder, "Test.json");
		FileWriter writer = new FileWriter(fOut);
		skaterInitial.getSkaterJSONObject().writeJSONString(writer);
		writer.flush();
		writer.close();
		File f2 = new File(subfolder, "Test.json");
		JSONParser testParser = new JSONParser();
		JSONObject testObject = (JSONObject) testParser.parse(new FileReader(f2));
		Skater testSkater = new Skater(testObject, ExistsInDB.YES);
		assertEquals("Different", true, skaterInitial.equals(testSkater));
		
	}


	@Test
	public void testConvertToArrayDeque(){
		JSONArray array = new JSONArray();
		array.add(new Long(5));
		array.add(new Long(99));
		ArrayDeque<Integer> d = Skater.convertToArrayDeque(array);
		assertEquals(new Integer(5), d.pop());
		assertEquals(new Integer(99), d.pop());
		
	}
	
	@Test
	public void testConvertToJSONArray(){
		ArrayDeque<Integer> deque = new ArrayDeque<Integer>(10);
		deque.add(new Integer(98));
		deque.add(new Integer(52));
		assertEquals("Wrong initial size", 2, deque.size());
		JSONArray a = Skater.convertToJSONArray(deque);
		assertEquals("Wrong size", 2, a.size());
		assertEquals(a.get(0), new Integer(98));
		assertEquals(a.get(1), new Integer(52));
	}
	
}
