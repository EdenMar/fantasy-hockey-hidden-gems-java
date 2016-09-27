package ca.ualberta.emar;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class SkaterTest {


	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	/*
	 * This test checks if the NHL json format can be accepted and the object
	 * instantiated properly
	 */
	@Test
	public void testConstructorWhenNewToDB() throws Exception, IOException, ParseException{
		Skater skater;

		File f = new File("test/Aaron Ekblad Daily Stats 1.json");
		JSONParser p = new JSONParser();
		JSONObject o = (JSONObject)p.parse(new FileReader(f));
		skater = new Skater(o, DataFromDB.NO);

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
	
	/*
	 * Test to instantiate a Skater from the json format I am using
	 */
	@Test
	public void testConstructorWhenDBExists() throws Exception, IOException, ParseException{
		Skater skater;
		File f = new File("test/Connor McDavid.json");
		JSONParser p = new JSONParser();
		JSONObject o = (JSONObject)p.parse(new FileReader(f));
		skater = new Skater(o, DataFromDB.YES);
		
		
		assertEquals("Goals wrong", skater.getTotalGoals(), 16);
		ArrayDeque<Integer> a = new ArrayDeque<Integer>(10);
		a.add(32);
		assertArrayEquals("Assists wrong", a.toArray(), skater.getAssists().toArray());
		
		assertEquals("shGoals wrong", 0, skater.getshGoals());
		
	}
	
	/*
	 * Test to see if the Skater object created can be written to file and
	 * reinstantiated
	 */
	@Test
	public void testFromJSONAndBackAgain() throws IOException, ParseException{
		Skater skaterInitial;
		File f = new File("test/Connor McDavid.json");
		JSONParser parser = new JSONParser();
		JSONObject object = (JSONObject)parser.parse(new FileReader(f));
		skaterInitial = new Skater(object, DataFromDB.YES);
		
		File subfolder = folder.newFolder("subfolder");
		File fOut = new File(subfolder, "Test.json");
		FileWriter writer = new FileWriter(fOut);

		writer.write(skaterInitial.getSkaterJSONObject().toJSONString());

		writer.flush();
		writer.close();
		
		
		File f2 = new File(subfolder, "Test.json");
		JSONParser testParser = new JSONParser();
		JSONObject testObject = (JSONObject) testParser.parse(new FileReader(f2));
		Skater testSkater = new Skater(testObject, DataFromDB.YES);
		
		assertTrue(skaterInitial.equals(testSkater));
		assertTrue(testSkater.equals(skaterInitial));
	}

	/*
	 * Test to see if conversion from JSON array to ArrayDeque is done
	 * correctly
	 */
	@Test
	public void testConvertToArrayDeque(){
		JSONArray array = new JSONArray();
		array.add(new Long(5));
		array.add(new Long(99));
		ArrayDeque<Integer> d = Skater.convertToArrayDeque(array);
		assertEquals(new Integer(5), d.pop());
		assertEquals(new Integer(99), d.pop());
		
	}
	/*
	 * Test to see if conversion from ArrayDeque to JSONArray is done 
	 * correctly
	 */
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
	/*
	 * Testing for whether two Skater objects hold the same data, ie equal.
	 */
	@Test
	public void testEquals() throws FileNotFoundException, IOException, ParseException{
		Skater inDB;
		File f = new File("test/Connor McDavid.json");
		JSONParser inDBParser = new JSONParser();
		JSONObject inDBObject = (JSONObject)inDBParser.parse(new FileReader(f));
		inDB = new Skater(inDBObject, DataFromDB.YES);
		
		Skater inDB2;
		JSONParser inDBParser2 = new JSONParser();
		JSONObject inDBObject2 = (JSONObject)inDBParser2.parse(new FileReader(f));
		inDB2 = new Skater(inDBObject2, DataFromDB.YES);
		
		Skater inDB3;
		JSONParser inDBParser3 = new JSONParser();
		JSONObject inDBObject3 = (JSONObject)inDBParser3.parse(new FileReader(f));
		inDB3 = new Skater(inDBObject3, DataFromDB.YES);
		
		Skater noDB;
		File f2 = new File("test/Connor McDavid Daily Stats.json");
		JSONParser noDBParser = new JSONParser();
		JSONObject noDBObject = (JSONObject)noDBParser.parse(new FileReader(f2));
		noDB = new Skater(noDBObject, DataFromDB.NO);
		Object o = new Object();
		//https://examples.javacodegeeks.com/core-java/lang/java-equals-method-example/
		assertFalse(inDB.equals(o));
		//reflexive
		assertTrue(inDB.equals(inDB));
		assertTrue(noDB.equals(noDB));
		
		//symmetric
		assertTrue(inDB.equals(inDB2));
		assertTrue(inDB2.equals(inDB));
		
		//transitive
		boolean t = inDB.equals(inDB2) && inDB2.equals(inDB3);
		assertTrue(t);
		assertEquals("Not transitive!", inDB.equals(inDB3), t);
		
		//DB and non DB skaters identical?
		assertTrue(noDB.equals(inDB));
		
	}
	
}
