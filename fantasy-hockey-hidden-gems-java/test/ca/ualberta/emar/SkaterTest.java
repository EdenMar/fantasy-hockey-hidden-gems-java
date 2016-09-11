package ca.ualberta.emar;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import ca.ualberta.emar.Skater;
import ca.ualberta.emar.Skater.ExistsInDB;

public class SkaterTest {


	/*
	 * This test checks if the data from the test can be properly instantiated in an instance
	 * of Skater. 
	 */
	@Test
	public void testConstructorWhenNewToDB() throws Exception, IOException, ParseException{
		Skater skater;

		File f = new File("src/test/Aaron Ekblad Daily Stats 1.json");
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
		File f = new File("src/test/Connor McDavid.json");
		JSONParser p = new JSONParser();
		JSONObject o = (JSONObject)p.parse(new FileReader(f));
		skater = new Skater(o, ExistsInDB.YES);
		
		
		assertEquals("Goals wrong", skater.getTotalGoals(), 16);
		ArrayDeque<Integer> a = new ArrayDeque<Integer>(10);
		a.add(32);
		assertArrayEquals("Assists wrong", a.toArray(), skater.getAssists().toArray());
		
		assertEquals("shGoals wrong", 0, skater.getshGoals());
		
	}
	
	@Test
	public void testFromJSONAndBackAgain() throws IOException, ParseException{
		Skater skater;
		File f = new File("src/test/Connor McDavid.json");
		JSONParser parser = new JSONParser();
		JSONObject object = (JSONObject)parser.parse(new FileReader(f));
		skater = new Skater(object, ExistsInDB.YES);
		TemporaryFolder folder = new TemporaryFolder();
		
	}


}
