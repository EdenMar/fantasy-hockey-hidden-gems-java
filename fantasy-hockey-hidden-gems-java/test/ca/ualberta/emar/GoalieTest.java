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
		Goalie inDB, inDB2, inDB3, noDB;
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
		inDB = new Goalie(inDBObject, DataFromDB.YES);
		inDB2 = new Goalie(inDBObject2, DataFromDB.YES);
		inDB3 = new Goalie(inDBObject3, DataFromDB.YES);
		noDB = new Goalie(noDBObject, DataFromDB.NO);
		
		Object o = new Object();
		
		assertFalse(inDB.equals(o));
		
		assertTrue(inDB.equals(inDB));
		assertTrue(noDB.equals(noDB));
		
		assertTrue(inDB.equals(inDB2));
		assertTrue(inDB2.equals(inDB));
		
		boolean t = inDB.equals(inDB2) && inDB2.equals(inDB3);
		assertTrue(t);
		assertEquals(inDB.equals(inDB3), t);
		
		assertTrue(noDB.equals(inDB));
	}
	
	@Test
	public void testGetGoalieJSON() throws FileNotFoundException, IOException, ParseException{
		Goalie initialG;
		File f = new File("test/Curtis McElhinney Daily Stats 1.json");
		JSONParser parseInitial = new JSONParser();
		JSONObject objInitial = (JSONObject)parseInitial.parse(new FileReader(f));
		initialG = new Goalie(objInitial, DataFromDB.NO);
		
		File subfolder = tmpFolder.newFolder("subfolder");
		File outfile = new File(subfolder, "Test.json");
		FileWriter writer = new FileWriter(outfile);
		
		writer.write(initialG.getGoalieJSON().toJSONString());
		writer.flush();
		writer.close();
		
		JSONParser parseFinal = new JSONParser();
		JSONObject objFinal = (JSONObject)parseFinal.parse(new FileReader(outfile));
		Goalie finalG = new Goalie(objFinal, DataFromDB.YES);
		
		assertTrue(initialG.equals(finalG));
	}

}
