import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	protected static void getDailyStats() throws Exception{
		File dir = new File("Daily Stats");
		
		if (!dir.exists()){
			dir.mkdir();
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String now = new String(sdf.format(date));
		
        URL site = new URL("http://www.nhl.com/stats/rest/grouped/skaters/season/skatersummary?cayenneExp=seasonId=20152016%20and%20gameTypeId=2");

		try{
	        URLConnection connection = site.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        String inputLine;
	        File skaterFile = new File(dir, now + " Skater Stats.json");
	        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(skaterFile), "utf-8"));
	        while ((inputLine = in.readLine()) != null) 
	            writer.write(inputLine);
	        in.close();
	        writer.close();
		} catch (Exception e) {
			System.out.println("An error occurred: " + e);
		}
		
		site = new URL("http://www.nhl.com/stats/rest/grouped/goalies/season/goaliesummary?cayenneExp=seasonId=20152016%20and%20gameTypeId=2%20and%20playerPositionCode=%22G%22");
		
		try{
	        URLConnection connection = site.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        String inputLine;
	        File goalieFile = new File(dir, now + " Goalie Stats.json");
	        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(goalieFile), "utf-8"));
	        while ((inputLine = in.readLine()) != null) 
	            writer.write(inputLine);
	        in.close();
	        writer.close();
		} catch (Exception e) {
			System.out.println("An error occurred: " + e);
		}
	}
	
	private static void createDatabase(){
		File dir = new File("Player Stats");
	}
	
	private static void createSkaterDatabase(){
		File dir = new File("Player Stats/Skater Stats");
		
	}
	
}
