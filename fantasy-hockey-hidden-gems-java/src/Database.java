import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
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

	private Database(){
		
	}
	
	public static Database getInstance(){
		if (d == null){
			d = new Database();
		}
		return d;
	}
	
	protected static void getDailyStats(File file) throws Exception{
		File dir = new File(file, "Daily Stats");
		
		if (!dir.exists()){
			dir.mkdir();
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String now = new String(sdf.format(date));
		
        URL site = new URL("http://www.nhl.com/stats/rest/grouped/skaters/season/skatersummary?cayenneExp=seasonId=20152016%20and%20gameTypeId=2");

        File skaterFile = new File(dir, now + " Skater Stats.json");
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
				System.out.println("An error occurred in getDailyStats(): " + e);
			} 
		}
		site = new URL("http://www.nhl.com/stats/rest/grouped/goalies/season/goaliesummary?cayenneExp=seasonId=20152016%20and%20gameTypeId=2%20and%20playerPositionCode=%22G%22");
        File goalieFile = new File(dir, now + " Goalie Stats.json");

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
				System.out.println("An error occurred in getDailyStats(): " + e);
			} 
		}
	}
	
	
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
	
	protected static void updateSkaterDatabase(){
		File fOut = new File("Player Stats/Skater Stats/");
		File fIn = new File("Daily Stats/");
		
		if (!fOut.exists()){
			fOut.mkdirs();
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String now = new String(sdf.format(date));
		
		try{
	        File readFile = new File(fIn, now + " Skater Stats.json");
	        JSONParser parser = new JSONParser();
	        JSONObject obj = (JSONObject) parser.parse(new FileReader(readFile));
	        JSONArray array = (JSONArray) obj.get("data");
	        for (Object o : array){
	        	JSONObject skater = (JSONObject) o;
	        	String name = (String) skater.get("playerName");

	        	File skaterFile = new File(fOut, name + ".json");
	        	if (!skaterFile.exists()){
	        		
	        	}
	        }
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}
	
	private static void updateGoalieDatabase(){
		
	}
	
	private static void createSkaterFile(String playerName){
		
	}
	
}
