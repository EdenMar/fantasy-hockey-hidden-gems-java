package main;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class DatabaseTest {
	
	Database d = Database.getInstance();
	public static final Date DATE = new Date();
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public static final String NOW = new String(sdf.format(DATE));

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
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
			File skaterControlFile = new File("src/test/Skater Control.json");
			File goalieControlFile = new File("src/test/Goalie Control.json");
			
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

	        assertEquals("Skater comparison problems", true, skaterControlArray.equals(skaterTestArray));

		} catch (Exception e){
			System.out.println(e);
		}
	}
	
	@Test
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
	
//	@Test
	public void testUpdateSkaterDatabase(){

		
		try {
			File subfolder = folder.newFolder("subfolder");
			//updateSkaterDatabase needs to happen after the DailyStats have been retrieved; for testing
			//purposes, need to create a dummy destination and files
			File dummyDailyFolder = new File(subfolder, "Daily Stats");
			dummyDailyFolder.mkdir();
			File dummyDailyStats = new File(dummyDailyFolder, NOW + " Skater Stats.json");
			BufferedReader br = new BufferedReader(new FileReader("src/test/Aaron Ekblad Daily Stats 1.json"));
			FileWriter writer = new FileWriter(dummyDailyStats);
			try{
				String line;
				while((line = br.readLine()) != null){
					writer.write(line);
				}
				 
			}catch (Exception e){
				e.printStackTrace();
			} finally{
				writer.close();
				br.close();
			}
			Database.manageSkaterDatabase(dummyDailyFolder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
