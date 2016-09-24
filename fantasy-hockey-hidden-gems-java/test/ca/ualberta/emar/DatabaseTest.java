package ca.ualberta.emar;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.sun.org.apache.bcel.internal.util.Objects;

public class DatabaseTest {
	
	Database d = Database.getInstance();
	public static final Date DATE = new Date();
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public static final String NOW = new String(sdf.format(DATE));

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

//	@Test
	public void testGetDailyStatsCorrectness() {

		try{
			//getDailyStats takes a File object as arg, not a TemporaryFolder object, so need for subfolder variable
			File subfolder = folder.newFolder("subfolder");
			Database.getDailyStats(subfolder);
			File createdDir = new File(subfolder, "Daily Stats");
			assertEquals("Daily Stats folder not created", true, createdDir.exists());

			File skaterTestFile = new File(createdDir, NOW + " Skater Stats.json");
			File goalieTestFile = new File(createdDir, NOW + " Goalie Stats.json");
			assertEquals("Skater Stats file not created", true, skaterTestFile.exists());
			assertEquals("Goalie Stats file not created", true, goalieTestFile.exists());
			File skaterControlFile = new File("test/Skater Control.json");
			File goalieControlFile = new File("test/Goalie Control.json");
			
			//parse the file
	        JSONParser skaterTestParser = new JSONParser();
	        //turn file into JSONObject
	        JSONObject skaterTestObj = (JSONObject) skaterTestParser.parse(new FileReader(skaterTestFile));
	        //put actual data into an array
	        JSONArray skaterTestData = (JSONArray) skaterTestObj.get("data");
	        //prep an array to sort the data
	        List<JSONObject> skaterTestArray = new ArrayList<JSONObject>();
	        for (int i = 0; i< skaterTestData.size(); i++){
	          	skaterTestArray.add((JSONObject)skaterTestData.get(i));
	        }
	        //sort
	        Collections.sort(skaterTestArray, new JSONComparator());

	        
	        JSONParser goalieTestParser = new JSONParser();
	        JSONObject goalieTestObj = (JSONObject) goalieTestParser.parse(new FileReader(goalieTestFile));
	        JSONArray goalieTestData = (JSONArray) goalieTestObj.get("data");
	        List<JSONObject> goalieTestArray = new ArrayList<JSONObject>();
	        for (int i = 0; i < goalieTestData.size(); i++){
	        	goalieTestArray.add((JSONObject)goalieTestData.get(i));
	        }
	        Collections.sort(goalieTestArray, new JSONComparator());
	        
	        JSONParser skaterControlParser = new JSONParser();
	        JSONObject skaterControlObj = (JSONObject) skaterControlParser.parse(new FileReader(skaterControlFile));
	        JSONArray skaterControlData = (JSONArray) skaterControlObj.get("data");
	        List<JSONObject> skaterControlArray = new ArrayList<JSONObject>();
	        for (int i = 0; i < skaterControlData.size(); i++){
	        	skaterControlArray.add((JSONObject)skaterControlData.get(i));
	        }
	        Collections.sort(skaterControlArray, new JSONComparator());
	        
	    
	        JSONParser goalieControlParser = new JSONParser();
	        JSONObject goalieControlObj = (JSONObject) goalieControlParser.parse(new FileReader(goalieControlFile));
	        JSONArray goalieControlData = (JSONArray) goalieControlObj.get("data");
	        List<JSONObject> goalieControlArray = new ArrayList<JSONObject>();
	        for (int i = 0; i < goalieControlData.size(); i++){
	        	goalieControlArray.add((JSONObject)goalieControlData.get(i));
	        }
	        Collections.sort(goalieControlArray, new JSONComparator());
	        
	        assertEquals("Goalie comparison problems", true, goalieControlArray.equals(goalieTestArray));

	        assertArrayEquals("Skater comparison problems", skaterControlArray.toArray(), skaterTestArray.toArray());

		} catch (Exception e){
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
//	@Test
	public void testGetDailyStatsNumberFiles(){
		try{
			File subfolder = folder.newFolder("subfolder");
			Database.getDailyStats(subfolder);
			Database.getDailyStats(subfolder);
			File createdDir = new File(subfolder, "Daily Stats");
			assertEquals("Wrong number of files created", createdDir.listFiles().length, 2);
		} catch (Exception e){
			System.out.println(e);
		}
	}
	
	/*
	 * This tests createSkaterDatabase() whether it can create a new skater in the DB from
	 * the NHL JSON format
	 */
	@Test
	public void testCreateSkaterFile(){

		
		try {
			File subfolder = folder.newFolder("subfolder");
			//
			File testFile = new File("test/Aaron Ekblad Daily Stats 1.json");
			JSONParser parser = new JSONParser();
			JSONObject initialPlayer = (JSONObject)parser.parse(new FileReader(testFile));
			File createdFile = new File(subfolder, "Aaron Ekblad.json");
			assertFalse(createdFile.exists());
			Skater s = new Skater(initialPlayer, DataFromDB.NO);
			Database.createSkaterFile(s, createdFile);
			assertTrue(createdFile.exists());
			JSONParser testParser = new JSONParser();
			JSONObject finalPlayer = (JSONObject)testParser.parse(new FileReader(createdFile));
			Skater initialSkater = new Skater(initialPlayer, DataFromDB.NO);
			Skater finalSkater = new Skater(finalPlayer, DataFromDB.YES);
			assertTrue(initialSkater.equals(finalSkater));


		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Tests whether the DB file of a given skater is updated correctly
	 */
	@Test
	public void testUpdateSkaterFile() throws FileNotFoundException, IOException, ParseException{
		File oldDataFile = new File("test/Aaron Ekblad Daily Stats 1.json");
		File newDataFile = new File("test/Aaron Ekblad Daily Stats 2.json");
		JSONParser parseOldData = new JSONParser();
		JSONParser parseNewData = new JSONParser();
		JSONObject oldJSONObject = (JSONObject)parseOldData.parse(new FileReader(oldDataFile));
		JSONObject newJSONObject = (JSONObject)parseNewData.parse(new FileReader(newDataFile));
		Skater oldSkater = new Skater(oldJSONObject, DataFromDB.NO);
		Skater newSkater = new Skater(newJSONObject, DataFromDB.NO);

		File subfolder = folder.newFolder("subfolder");
		File testOutputFile = new File(subfolder, "Testoutput.json");
		Database.updateSkaterFile(oldSkater, newSkater, testOutputFile);
		assertTrue("Test output file not made", testOutputFile.exists());
		File loadTestOutputFile = new File(subfolder, "Testoutput.json");
		File controlOutputFile = new File("test/Aaron Ekblad.json");
		
		JSONParser parseTestFile = new JSONParser();
		JSONParser parseControlFile = new JSONParser();
		
		JSONObject testJSONObject = (JSONObject)parseTestFile.parse(new FileReader(loadTestOutputFile));
		JSONObject controlJSONObject = (JSONObject)parseControlFile.parse(new FileReader(controlOutputFile));
		
		Skater testSkater = new Skater(testJSONObject, DataFromDB.YES);
		Skater controlSkater = new Skater(controlJSONObject, DataFromDB.YES);
		assertTrue("Not the same Skater object", controlSkater.equals(testSkater));
	}
	
	@Test
	public void testUpdateStatQueue() {
		ArrayDeque<Integer> d1 = new ArrayDeque<Integer>(10);
		
		d1.add(1);
		d1.add(2);
		d1.add(3);
		d1.add(4);
		d1.add(5);
		d1.add(6);
		d1.add(7);
		d1.add(8);
		d1.add(9);
		
		Database.updateStatQueue(d1, 10);
		assertTrue("Wrong size", d1.size() == 10);
		Database.updateStatQueue(d1, 11);
		assertTrue("Wrong size", d1.size() == 10);
		assertTrue("Front of queue wrong", d1.peek()==2);
		
	}

	class JSONComparator implements Comparator<JSONObject>{

		@Override
		public int compare(JSONObject player1, JSONObject player2) {
			String name1 = (String)player1.get("playerName");
			String name2 = (String)player2.get("playerName");
			
			return name1.compareTo(name2);
		}
		
	}
	

}
