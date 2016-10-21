package ca.ualberta.emar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {

		Database.getInstance();
		try {
			Database.getDailyStats();
			Database.manageSkaterDatabase();
			Database.manageGoalieDatabase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Path cwd = Paths.get("");
		String s = cwd.toAbsolutePath().toString();
		File currentDir = new File(s);
		File goalieStats = new File(currentDir, "Player Stats/Goalie Stats/");
		File skaterStats = new File(currentDir, "Player Stats/Skater Stats/");
		
		File[] goalieFiles = getFiles(goalieStats);
		File[] skaterFiles = getFiles(skaterStats);
		
		IntegerReport goalsReport = new IntegerReport("goals");
		IntegerReport assistsReport = new IntegerReport("assists");
		IntegerReport pointsReport = new IntegerReport("points");
		IntegerReport penaltiesReport = new IntegerReport("penalties");
		IntegerReport ppPointsReport = new IntegerReport("ppPoints");
		IntegerReport ppGoalsReport = new IntegerReport("ppGoals");
		
		FloatReport savePctgReport = new FloatReport("savePctg");
		FloatReport GAAReport = new FloatReport("GAA");
		IntegerReport winsReport = new IntegerReport("wins");
		IntegerReport shotsAgainstReport = new IntegerReport("shotsAgainst");
		IntegerReport timeOnIceReport = new IntegerReport("timeOnIce");
		IntegerReport savesReport = new IntegerReport("saves");
		
		List<IntegerReport> reportList = new ArrayList<IntegerReport>(Arrays.asList(goalsReport, assistsReport, 
				pointsReport, penaltiesReport, ppPointsReport, ppGoalsReport, winsReport, shotsAgainstReport, timeOnIceReport, savesReport));
		
		
		for (File skaterfile : skaterFiles){
			JSONParser parser = new JSONParser();
			JSONObject jsonSkater = (JSONObject) parser.parse(new FileReader(skaterfile));
			Skater skater = new Skater(jsonSkater, DataFromDB.YES);
			goalsReport.add(skater.getName(), skater.getGoals());
			assistsReport.add(skater.getName(), skater.getAssists());
			pointsReport.add(skater.getName(), skater.getPoints());
			penaltiesReport.add(skater.getName(), skater.getPenaltyMinutes());
			ppPointsReport.add(skater.getName(), skater.getppPoints());
			ppGoalsReport.add(skater.getName(), skater.getppGoals());
			System.out.println(skater.getGoals());
		}
		
		for (File goaliefile : goalieFiles){
			JSONParser parser = new JSONParser();
			JSONObject jsonGoalie = (JSONObject)parser.parse(new FileReader(goaliefile));
			Goalie goalie = new Goalie(jsonGoalie, DataFromDB.YES);
			savePctgReport.add(goalie.getName(), goalie.getSavePctg());
			GAAReport.add(goalie.getName(), goalie.getGoalsAgainstAverage());
			winsReport.add(goalie.getName(), goalie.getWins());
			shotsAgainstReport.add(goalie.getName(), goalie.getShotsAgainst());
			timeOnIceReport.add(goalie.getName(), goalie.getTimeOnIce());
			savesReport.add(goalie.getName(), goalie.getSaves());
			
		}
		
		for (IntegerReport report : reportList){
			report.createReport(1);
		}
		
		GAAReport.createReport(1);
		savePctgReport.createReport(1);
	}
	
	
	public static File[] getFiles(File filepath){
		File[] files = filepath.listFiles();
		return files;

	}
}
