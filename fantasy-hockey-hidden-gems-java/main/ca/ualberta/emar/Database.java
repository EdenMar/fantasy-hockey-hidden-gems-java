package ca.ualberta.emar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


class Database {
	
	private static Database d = null;
	
	private static final Date DATE = new Date();
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static final String NOW = new String(sdf.format(DATE));

	private Database(){
		
	}
	
	public static Database getInstance(){
		if (d == null){
			d = new Database();
		}
		return d;
	}
	/*
	 * Downloads the latest skater and goalie stats and saves it to the provided file location
	 */
	protected static void getDailyStats(File file) throws Exception{
		File dir = new File(file, "Daily Stats");
		
		if (!dir.exists()){
			dir.mkdir();
		}

		
        URL site = new URL("http://www.nhl.com/stats/rest/grouped/skaters/season/skatersummary?cayenneExp=seasonId=20152016%20and%20gameTypeId=2");

        File skaterFile = new File(dir, NOW + " Skater Stats.json");
		if (!skaterFile.exists()) {
			try {
				URLConnection connection = site.openConnection();
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				
				Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(skaterFile), "utf-8"));
				while ((inputLine = in.readLine()) != null)
					writer.write(inputLine);
				in.close();
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("An error occurred in getDailyStats(): " + e);
			} 
		}
		site = new URL("http://www.nhl.com/stats/rest/grouped/goalies/season/goaliesummary?cayenneExp=seasonId=20152016%20and%20gameTypeId=2%20and%20playerPositionCode=%22G%22");
        File goalieFile = new File(dir, NOW + " Goalie Stats.json");

		if (!goalieFile.exists()) {
			try {
				URLConnection connection = site.openConnection();
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(goalieFile), "utf-8"));
				while ((inputLine = in.readLine()) != null)
					writer.write(inputLine);
				in.close();
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("An error occurred in getDailyStats(): " + e);
			} 
		}
	}
	
	/*
	 * Same as above, but assumes the current working directory will be used to store data if no argument given
	 */
	protected static void getDailyStats() throws Exception{
		Path cwd = Paths.get("");
		String s = cwd.toAbsolutePath().toString();
		File f = new File(s);
		try{
			Database.getDailyStats(f);
		} catch (Exception e){
			System.out.println("An error occurred in getDailyStats()");
		}
		
	}
	/*
	 * Like getDailyStats(), assumes current working directory is where work will be
	 * done
	 */
	protected static void manageSkaterDatabase(){
		Path cwd = Paths.get("");
		String s = cwd.toAbsolutePath().toString();
		File f = new File(s);
		try{
			Database.manageSkaterDatabase(f);
		} catch (Exception e){
			System.out.println("An error occurred in updateSkaterDatabase");
		}
	}
	/*
	 * This method takes the newest stats and updates the individual skater files. Takes
	 * a filepath as an argument
	 */
	protected static void manageSkaterDatabase(File file){
		//fOut represents the path where the skater stats are stored for individual players
		File fOut = new File(file, "Player Stats/Skater Stats/");
		//fIn represents the path where the daily stats are kept
		File fIn = new File(file, "Daily Stats/");
		
		if (!fOut.exists()){
			fOut.mkdirs();
		}
		
		try{
	        File readFile = new File(fIn, NOW + " Skater Stats.json");
	        JSONParser parser = new JSONParser();
	        JSONObject obj = (JSONObject) parser.parse(new FileReader(readFile));
	        JSONArray skaterData = (JSONArray) obj.get("data");
	        for (Object elementOfSkaterData : skaterData){
	        	JSONObject skaterObject = (JSONObject) elementOfSkaterData;
	        	String name = (String) skaterObject.get("playerName");
	        	File skaterFile = new File(fOut, name + ".json");
	        	if (!skaterFile.exists()){
	        		createSkaterFile(skaterObject, skaterFile);
	        	}
	        	else{
	        		//skaterFile exists
	        		//compare skaterObject data with skaterFile data
	        		//if same, continue
	        		//if not, update the skaterFile
	        		//create new instance of Skater with old data
	        		//update database with new data based on comparisons
	        	}
	        }
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}
	
	private static void updateGoalieDatabase(){
		
	}
	/*
	 * createSkaterFile takes the JSONObject player and a filepath and creates the individual
	 * DB file for each skater
	 */
	protected static void createSkaterFile(JSONObject player, File filePath){
		Skater skater = new Skater(player, ExistsInDB.NO);
		//create new Skater instance
		//take data from the skaterObject
		//assign to Skater instance
		//write to file
		
		try{
			FileWriter writer = new FileWriter(filePath);
			skater.getSkaterJSONObject().writeJSONString(writer);
			writer.flush();
			writer.close();
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("Problem in createSkaterFile(): " + e);
		}
		
	}
	
	protected static void updateSkaterFile(JSONObject oldData, JSONObject newData){
		
	}
	
	
}
