package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import main.Skater;
import main.Skater.ExistsInDB;

public class SkaterTest {


//	@Before
//	public void setUp() throws Exception {
//		try{
//		File f = new File("Connor McDavid.json");
//		JSONParser p = new JSONParser();
//		JSONObject connor = (JSONObject)p.parse(new FileReader(f));
//		skater = new Skater(connor, ExistsInDB.YES);
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//		
//	}
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
		
		assertEquals("Games played wrong", skater.getTotalGamesPlayed(), 45);
		ArrayDeque<Integer> a = new ArrayDeque<Integer>(10);
		a.add(16);
		assertArrayEquals("Goals wrong", a.toArray(), skater.getGoals().toArray());
		
		
	}
	
	@Test
	public void testAssert(){
		ArrayDeque<Integer> a1 = new ArrayDeque<Integer>(10);
		ArrayDeque<Integer> a2 = new ArrayDeque<Integer>(10);
		assertArrayEquals(a1.toArray(), a2.toArray());
		
	}


}
