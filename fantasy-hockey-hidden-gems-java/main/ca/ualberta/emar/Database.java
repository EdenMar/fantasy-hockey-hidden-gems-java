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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
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
	        		Skater s = new Skater(skaterObject, DataFromDB.NO);
	        		createSkaterFile(s, skaterFile);
	        	}
	        	else{
	        		//skaterFile exists
	        		//compare skaterObject data with skaterFile data
	        		//if same, continue
	        		//if not, update the skaterFile
	        		//create new instance of Skater with old data
	        		//update database with new data based on comparisons
	        		
	        		//Skater object for new data
	        		Skater newSkater = new Skater(skaterObject, DataFromDB.NO);
	        		
	        		JSONParser dbParser = new JSONParser();
	        		JSONObject dbObject = (JSONObject)dbParser.parse(new FileReader(skaterFile));
	        		Skater dbSkater = new Skater(dbObject, DataFromDB.YES);
	        		updateSkaterFile(dbSkater, newSkater, skaterFile);
	        	}
	        }
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}
	
	protected static void manageGoalieDatabase(){
		Path cwd = Paths.get("");
		String s = cwd.toAbsolutePath().toString();
		File f = new File(s);
		try{
			Database.manageGoalieDatabase(f);
		} catch (Exception e){
			System.out.println("An error occurred in manageGoalieDatabase");
		}
	}
	
	protected static void manageGoalieDatabase(File path){
		File outFile = new File(path, "Player Stats/Goalie Stats");
		File inFile = new File(path, "Daily Stats/");
		
		if (!outFile.exists()){
			outFile.mkdirs();
		}
		
		try{
	        File readFile = new File(inFile, NOW + " Goalie Stats.json");
	        JSONParser parser = new JSONParser();
	        JSONObject obj = (JSONObject)parser.parse(new FileReader(readFile));
	        JSONArray array = new JSONArray();
	        array = (JSONArray)obj.get("data");
	        for (Object element : array){
	        	JSONObject goalieObj = (JSONObject)element;
	        	String name = (String)goalieObj.get("playerName");
	        	File goalieFile = new File(outFile, name + ".json");
	        	if (!goalieFile.exists()){
	        		Goalie g = new Goalie(goalieObj, DataFromDB.NO);
	        		createGoalieFile(g, goalieFile);
	        	}
	        	else{
	        		Goalie newGoalie = new Goalie(goalieObj, DataFromDB.NO);
	        		
	        		JSONParser dbParser = new JSONParser();
	        		JSONObject dbObj = (JSONObject)dbParser.parse(new FileReader(goalieFile));
	        		Goalie dbGoalie = new Goalie(dbObj, DataFromDB.YES);
	        		updateGoalieDatabase(dbGoalie, newGoalie, goalieFile);
	        	}
	        }
	        }catch (Exception e){
	        	e.printStackTrace();
	        }
	}
	
	protected static void createGoalieFile(Goalie player, File filePath){
		try{
			FileWriter writer = new FileWriter(filePath);
			player.getGoalieJSON().writeJSONString(writer);
			writer.flush();
			writer.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	protected static void updateGoalieDatabase(Goalie dbGoalie, Goalie newData, File filePath){
		
		if (dbGoalie.getTotalGamesPlayed() < newData.getTotalGamesPlayed()){
			dbGoalie.setTotalGamesPlayed(newData.getTotalGamesPlayed());
			
			//need temp vars to calculate before addin
			int tmpShot = newData.getTotalShotsAgainst() - dbGoalie.getTotalShotsAgainst();
			int tmpGoalsAgainst = newData.getTotalGoalsAgainst() - dbGoalie.getTotalGoalsAgainst();
			int tmpSaves = newData.getTotalSaves() - dbGoalie.getTotalSaves();
			int tmpTime = newData.getTotalTimeOnIce() - dbGoalie.getTotalTimeOnIce();
			int tmpMin = tmpTime / 60;
			float tmpGAA = tmpGoalsAgainst * 60f / tmpMin;

			float tmpSavePctg = (float)tmpSaves / tmpShot;

			updateStatQueue(dbGoalie.getSavePctg(), tmpSavePctg);
			dbGoalie.setTotalSavePctg(newData.getTotalSavePctg());
			
			updateStatQueue(dbGoalie.getWins(), newData.getTotalWins() - dbGoalie.getTotalWins());
			dbGoalie.setTotalWins(newData.getTotalWins());
			
			dbGoalie.setShutouts(newData.getShutouts());
			
			updateStatQueue(dbGoalie.getShotsAgainst(), tmpShot);
			dbGoalie.setTotalShotsAgainst(newData.getTotalShotsAgainst());
			
			updateStatQueue(dbGoalie.getGoalsAgainst(), tmpGoalsAgainst);
			dbGoalie.setTotalGoalsAgainst(newData.getTotalGoalsAgainst());
			
			updateStatQueue(dbGoalie.getSaves(), tmpSaves);
			dbGoalie.setTotalSaves(newData.getTotalSaves());
			
			updateStatQueue(dbGoalie.getGoalsAgainstAverage(), tmpGAA);
			dbGoalie.setTotalGAA(newData.getTotalGAA());
			
			updateStatQueue(dbGoalie.getTimeOnIce(), tmpTime);
			dbGoalie.setTotalTimeOnIce(newData.getTotalTimeOnIce());
			
			createGoalieFile(dbGoalie, filePath);
		}
	}
	/*
	 * createSkaterFile takes the Skater object and a filepath and creates the individual
	 * DB file for each skater or overwrites the file
	 */
	protected static void createSkaterFile(Skater player, File filePath){
		//create new Skater instance
		//take data from the skaterObject
		//assign to Skater instance
		//write to file
		
		try{
			FileWriter writer = new FileWriter(filePath);
			player.getSkaterJSONObject().writeJSONString(writer);
			writer.flush();
			writer.close();
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("Problem in createSkaterFile(): " + e);
		}
		
	}
	
	protected static void updateSkaterFile(Skater dbData, Skater newData, File filepath){

		
		if (dbData.getTotalGamesPlayed() < newData.getTotalGamesPlayed()){
			
			dbData.setTotalGamesPlayed(newData.getTotalGamesPlayed());
			
			Database.updateStatQueue(dbData.getGoals(), newData.getTotalGoals() - dbData.getTotalGoals());
			dbData.setTotalGoals(newData.getTotalGoals());
			
			Database.updateStatQueue(dbData.getAssists(), newData.getTotalAssists() - dbData.getTotalAssists());
			dbData.setTotalAssists(newData.getTotalAssists());
			
			Database.updateStatQueue(dbData.getPoints(), newData.getTotalPoints()- dbData.getTotalPoints());
			dbData.setTotalPoints(newData.getTotalPoints());
			
			Database.updateStatQueue(dbData.getPlusMinus(), newData.getTotalPlusMinus() - dbData.getTotalPlusMinus());
			dbData.setTotalPlusMinus(newData.getTotalPlusMinus());
			
			Database.updateStatQueue(dbData.getPenaltyMinutes(), newData.getTotalPenaltyMinutes() - dbData.getTotalPenaltyMinutes());
			dbData.setTotalPenaltyMinutes(newData.getTotalPenaltyMinutes());
			
			Database.updateStatQueue(dbData.getppPoints(), newData.getTotalppPoints() - dbData.getTotalppPoints());
			dbData.setTotalppPoints(newData.getTotalppPoints());
			
			Database.updateStatQueue(dbData.getppGoals(), newData.getTotalppGoals() - dbData.getTotalppGoals());
			dbData.setTotalppGoals(newData.getTotalppGoals());
			
			Database.updateStatQueue(dbData.getShots(), newData.getTotalShots() - dbData.getTotalShots());
			dbData.setTotalShots(newData.getTotalShots());
			
			dbData.setshGoals(newData.getshGoals());
			
			Database.createSkaterFile(dbData, filepath);
		}
		else{
			System.out.println("Ignore");
		}
	}
	
	protected static void updateStatQueue(ArrayDeque<Integer> statBefore, int difference){
		if (statBefore.size() >= 10){
			statBefore.removeFirst();
		}
		statBefore.add(difference);
	}
	
	protected static void updateStatQueue(ArrayDeque<Float> statBefore, float difference){
		if (statBefore.size() >= 10){
			statBefore.removeFirst();
		}
		DecimalFormat df = new DecimalFormat("0.0000");
		Float f = new Float(df.format(difference));

		statBefore.add(f);
	}	
	
	
}
