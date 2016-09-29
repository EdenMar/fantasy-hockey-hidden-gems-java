package ca.ualberta.emar;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class GoalieTest {

	@Rule
	public TemporaryFolder tmpFolder = new TemporaryFolder();
	
	@Test
	public void testConstructorForNoDBData() throws Exception, IOException, ParseException{
		Goalie goalie;
		
		File f = new File("test/Curtis McElhinney Daily Stats 1.json");
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(new FileReader(f));
		
		goalie = new Goalie(obj, DataFromDB.NO);
		
		assertEquals("Wrong name", goalie.getName(), "Curtis McElhinney");
		assertTrue("Wrong goals against", goalie.getTotalGoalsAgainst() == 46);
		ArrayDeque<Float> savePctg = new ArrayDeque<Float>(10);
		savePctg.add(0.8896f);
		assertArrayEquals("Wins wrong", savePctg.toArray(), goalie.getSavePctg().toArray());
		
	}
	
	@Test
	public void testConstructorForDBData() throws Exception, IOException, ParseException{
		Goalie goalie;
		
		File f = new File("test/Curtis McElhinney.json");
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(new FileReader(f));
		
		goalie = new Goalie(obj, DataFromDB.YES);
		
		assertEquals("Wrong name", goalie.getName(), "Curtis McElhinney");
		ArrayDeque<Float> d = new ArrayDeque<>(10);
		d.addFirst(3.3036f);
		assertArrayEquals("Wrong GAA", goalie.getGoalsAgainstAverage().toArray(), d.toArray());
		assertTrue("Wrong shots against", goalie.getTotalShotsAgainst() == 417);
		
	}
	
	@Test
	public void testEquals() throws Exception, IOException, ParseException{
		Skater inDB, inDB2, inDB3, noDB;
		File f = new File("test/Curtis McElhinney.json");
		File f2 = new File("test/Curtis McElhinney Daily Stats 1.json");
		JSONParser inDBParser = new JSONParser();
		JSONObject inDBObject = (JSONObject)inDBParser.parse(new FileReader(f));
		JSONParser inDBParser2 = new JSONParser();
		JSONObject inDBObject2 = (JSONObject)inDBParser2.parse(new FileReader(f));
		JSONParser inDBParser3 = new JSONParser();
		JSONObject inDBObject3 = (JSONObject)inDBParser3.parse(new FileReader(f));
		JSONParser noDBParser = new JSONParser();
		JSONObject noDBObject = (JSONObject)noDBParser.parse(new FileReader(f2));
		
	}

}
