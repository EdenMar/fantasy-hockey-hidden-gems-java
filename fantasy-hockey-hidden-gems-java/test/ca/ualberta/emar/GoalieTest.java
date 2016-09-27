package ca.ualberta.emar;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;
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
	public void testConstructorForDBData() throws Exception, IOException, ParseException{
		Goalie goalie;
		
		File f = new File("test/Curtis McElhinney Daily Stats 1.json");
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(new FileReader(f));
		
		goalie = new Goalie(obj, DataFromDB.NO);
		
		assertTrue("Wrong name", goalie.getName() == "Curtis McElhinney");
		assertTrue("Wrong goals against", goalie.getTotalGoalsAgainst() == 46);
		ArrayDeque<Float> wins = new ArrayDeque<Float>(10);
		wins.add(2f);
		assertArrayEquals("Wins wrong", wins.toArray(), goalie.getWins().toArray());
		
		
	}

}
